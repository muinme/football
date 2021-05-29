package com.example.football.api.controllers;

import com.example.football.infrastructure.security.CookieUtil;
import com.example.football.infrastructure.security.JwtUtil;
import com.example.football.models.RequestMatch;
import com.example.football.services.RequestMatchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@RestController
@RequestMapping("/football")
public class RequestMatchController {
    private static final String jwtTokenCookieName = "JWT-TOKEN";

    @Autowired
    private RequestMatchService requestMatchService;

    @Autowired
    private JwtUtil jwtUtil;

    @Autowired
    private CookieUtil cookieUtil;

    @RequestMapping(value = {"/requestMatch/getAll"}, method = RequestMethod.GET)
    public List<RequestMatch> list() {
        return requestMatchService.listAllRequestMatch();
    }


    @RequestMapping(value = {"/requestMatch/getSlWait/{user_id}"}, method = RequestMethod.GET)
    public Integer getSlWait(@PathVariable Integer user_id) {
        return requestMatchService.getSlWait(user_id);
    }

    @RequestMapping(value = {"/requestMatch/create/{id}"}, method = RequestMethod.POST)
    public RequestMatch create(@PathVariable Integer id, HttpServletRequest httpServletRequest) {
        System.out.println(id);
        String jwt = cookieUtil.getValue(httpServletRequest, jwtTokenCookieName);
        if(null == jwt) {
            System.out.println("Chua login | khong the lay token trong cookie");
            // TODO return;
        }
        // kiem tra token duoc luu trong redis xem co hay khong
        // TODO
        // Neu dung thi tiep tuc
        String username = jwtUtil.getUsernameFromToken(jwt);
        System.out.println("username in cookie = " + username);
        return requestMatchService.createRequestMatch(id, username);
    }

    @RequestMapping(value = {"/requestMatch/Username"}, method = RequestMethod.GET)
    public List<RequestMatch> getRequestMatch(HttpServletRequest httpServletRequest) {
        String jwt = cookieUtil.getValue(httpServletRequest, jwtTokenCookieName);
        if(null == jwt) {
            System.out.println("Chua login | khong the lay token trong cookie");
            // TODO return;
        }
        // kiem tra token duoc luu trong redis xem co hay khong
        // TODO
        // Neu dung thi tiep tuc
        String username = jwtUtil.getUsernameFromToken(jwt);
        System.out.println("username in cookie = " + username);
        return requestMatchService.getRequestMatchByUsername(username);
    }
    @RequestMapping(value = {"/requestMatch/getSlWaitTeam/{football_id}"}, method = RequestMethod.GET)
    public Integer getSlWaitPitch(@PathVariable Integer football_id) {
        return requestMatchService.getSlWaitTeam(football_id);
    }
}
