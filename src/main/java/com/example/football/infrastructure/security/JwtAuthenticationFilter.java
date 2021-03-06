package com.example.football.infrastructure.security;

import com.example.football.repositories.AuthorizerRepository;
import com.example.football.services.UserService;
import io.jsonwebtoken.ExpiredJwtException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Slf4j
@Component
public class JwtAuthenticationFilter extends OncePerRequestFilter {

    @Autowired
    private JwtUtil jwtUtil;

    @Autowired
    private UserDetailsService jwtUserDetailsService;

    @Autowired
    private UserService userService;

    @Autowired
    private AuthorizerRepository authorizerRepository;

    @Override
    protected void doFilterInternal(HttpServletRequest httpServletRequest,
                                    HttpServletResponse httpServletResponse, FilterChain filterChain)
            throws ServletException, IOException {
        final String requestTokenHeader = httpServletRequest.getHeader("Authorization");

        String username = null;
        String jwtToken = null;

        String urlPath = httpServletRequest.getRequestURI();
        if (requestTokenHeader != null && requestTokenHeader.startsWith("Bearer ")) {
            jwtToken = requestTokenHeader.substring(7);
            try {
                username = jwtUtil.parseToken(httpServletRequest);
            } catch (IllegalArgumentException e) {
                System.out.println("Unable to get JWT Token");
            } catch (ExpiredJwtException e) {
                System.out.println("JWT Token has expired");
            }
        } else {
            logger.warn("JWT Token does not begin with Bearer String");
        }
        if (username != null && SecurityContextHolder.getContext().getAuthentication() == null) {
            UserDetails userDetails = this.jwtUserDetailsService.loadUserByUsername(username);
            if (jwtUtil.validateToken(jwtToken, userDetails, username)) {
                UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken(
                        userDetails, null, userDetails.getAuthorities());
                usernamePasswordAuthenticationToken
                        .setDetails(new WebAuthenticationDetailsSource().buildDetails(httpServletRequest));
                SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);
            }else {
                filterChain.doFilter(httpServletRequest, httpServletResponse);
            }
            boolean isAllow = false;
            try {
                //Truy v???n v??o CSDL theo username
                String apiPermissions = authorizerRepository.getApiByUsername(username, urlPath);
                System.out.println(username + " " + urlPath + " " + apiPermissions);
                //N???u c?? quy???n th??
                if(apiPermissions != null)isAllow = true;
            } catch (Exception e) {
                log.error(e.toString(), e);
                throw e;
            }
            if (isAllow == true){
                filterChain.doFilter(httpServletRequest, httpServletResponse);
            }
            if(isAllow == false){
                throw new ServletException("Error Authorizer Api");
            }
        }else{
            filterChain.doFilter(httpServletRequest, httpServletResponse);
        }
    }
}