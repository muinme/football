package com.example.football.services.Impl;

import com.example.football.infrastructure.security.CookieUtil;
import com.example.football.infrastructure.security.JwtUtil;
import com.example.football.models.User;
import com.example.football.repositories.UserRepository;
import com.example.football.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.*;

@Service
public class UserServiceImpl implements UserService, UserDetailsService {

    private static final String jwtTokenCookieName = "JWT-TOKEN";

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private JwtUtil jwtUtil;

    @Autowired
    private AuthenticationServiceImpl authenticationService;

    @Override
    public User createUser(User user) {
        user.setPassword(new BCryptPasswordEncoder().encode(user.getPassword()));
        user.setCreated(new Date());
        return userRepository.save(user);
    }

    @Override
    public User findByUsername(String username) {
        User user = userRepository.findByUsername(username);
        User userPrincipal = new User();
        if (null != user) {
            Set<String> authorities = new HashSet<>();
            userPrincipal.setUsername(user.getUsername());
            userPrincipal.setPassword(user.getPassword());
        }
        return userPrincipal;
    }

    @Override
    public String loginUser(UserDetails userDetails, HttpServletResponse httpServletResponse) {
        final String token = jwtUtil.generateToken(userDetails);
        CookieUtil.create(httpServletResponse, jwtTokenCookieName, token, false, -1, "localhost");
        return token;
    }

    @Override
    public String logoutUser(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) {
        jwtUtil.invalidateRelatedTokens(httpServletRequest);
        CookieUtil.clear(httpServletResponse, jwtTokenCookieName);
        return "logout....";
    }

    @Override
    public User getByIdUser(Integer id) {
        return userRepository.findById(id).get();
    }

    @Override
    public void saveUser(User user) {
        user.setPassword(new BCryptPasswordEncoder().encode(user.getPassword()));
        userRepository.save(user);
    }

    @Override
    public void deleteUser(User user) {
        user.setPassword(new BCryptPasswordEncoder().encode(user.getPassword()));
        userRepository.save(user);
    }

    @Override
    public List<User> listAllUser() {
        return userRepository.findAll();
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        User user = userRepository.findByUsername(username);
        if (user == null) {
            throw new UsernameNotFoundException("User not found with username: " + username);
        }
        return new org.springframework.security.core.userdetails.User(user.getUsername(), user.getPassword(),
                new ArrayList<>());
    }
}
