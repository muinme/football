package com.example.football.api.controllers;

import com.example.football.infrastructure.security.CookieUtil;
import com.example.football.infrastructure.security.JwtUtil;
import com.example.football.models.RequestMatch;
import com.example.football.models.RequestPitch;
import com.example.football.services.RequestMatchService;
import com.example.football.services.RequestPitchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import javax.servlet.http.HttpServletRequest;
import java.util.List;

@RestController
@RequestMapping("/football")
public class RequestPitchController {
    private static final String jwtTokenCookieName = "JWT-TOKEN";

    @Autowired
    private RequestPitchService requestPitchService;

    @Autowired
    private JwtUtil jwtUtil;
    @RequestMapping(value = {"/requestPitch/create/{pitch_id}/{pitch_detail_id}"}, method = RequestMethod.POST)
    public RequestPitch create(@PathVariable Integer pitch_id, @PathVariable Integer pitch_detail_id, HttpServletRequest httpServletRequest) {
//        String jwt = CookieUtil.getValue(httpServletRequest, jwtTokenCookieName);
//        if(null == jwt) {
//            System.out.println("Chua login | khong the lay token trong cookie");
//            // TODO return;
//        }
//        // kiem tra token duoc luu trong redis xem co hay khong
//        // TODO
//        // Neu dung thi tiep tuc
//        String username = jwtUtil.getUsernameFromToken(jwt);
//        System.out.println("username in cookie = " + username);
        return requestPitchService.createRequestPitch(pitch_id, pitch_detail_id, "a");
    }

    @RequestMapping(value = {"/requestPitch/Username"}, method = RequestMethod.GET)
    public List<RequestPitch> getRequestPitch(HttpServletRequest httpServletRequest) {
        String jwt = CookieUtil.getValue(httpServletRequest, jwtTokenCookieName);
        if(null == jwt) {
            System.out.println("Chua login | khong the lay token trong cookie");
            // TODO return;
        }
        // kiem tra token duoc luu trong redis xem co hay khong
        // TODO
        // Neu dung thi tiep tuc
        String username = jwtUtil.getUsernameFromToken(jwt);
        System.out.println("username in cookie = " + username);
        return requestPitchService.getRequestPitchByUsername(username);
    }
}