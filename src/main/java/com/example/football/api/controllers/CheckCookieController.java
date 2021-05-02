package com.example.football.api.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

@RestController
public class CheckCookieController {
    @GetMapping("football/test")
    public void getCookie(HttpServletRequest httpServletRequest){
        Cookie [] cookies = httpServletRequest.getCookies();
        for (Cookie cookie: cookies) {
            System.out.println(cookie.getValue());
            System.out.println(cookie.getName());
        }
    }

}
