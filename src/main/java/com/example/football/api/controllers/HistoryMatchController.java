package com.example.football.api.controllers;

import com.example.football.infrastructure.security.CookieUtil;
import com.example.football.infrastructure.security.JwtUtil;
import com.example.football.models.HistoryMatch;
import com.example.football.models.PieInfo;
import com.example.football.services.HistoryMatchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@RestController
@RequestMapping("/football")
public class HistoryMatchController {

    private static final String jwtTokenCookieName = "JWT-TOKEN";

    @Autowired
    private CookieUtil cookieUtil;

    @Autowired
    private JwtUtil jwtUtil;

    @Autowired
    private HistoryMatchService historyMatchService;

    @RequestMapping(value = {"/historyMatch/getAll"}, method = RequestMethod.GET)
    public List<HistoryMatch> list() {
        return historyMatchService.listAllHistoryMatch();
    }

    @RequestMapping(value = {"/historyMatch/getAllInfo"}, method = RequestMethod.GET)
    public ResponseEntity<List<PieInfo>> listPieInfoHistoryMatch() {
        return new ResponseEntity<List<PieInfo>>(this.historyMatchService.pieInfoList(), HttpStatus.OK);
    }

    @RequestMapping(value = {"/historyMatch1/create/{request_match_id}"}, method = RequestMethod.POST)
    public HistoryMatch createHistoryMatch1(@PathVariable Integer request_match_id) {
        return historyMatchService.saveHistoryMatch1(request_match_id);
    }
    @RequestMapping(value = {"/historyMatch2/create/{request_match_id}"}, method = RequestMethod.POST)
    public HistoryMatch createHistoryMatch2(@PathVariable Integer request_match_id) {
        return historyMatchService.saveHistoryMatch2(request_match_id);
    }

    @RequestMapping(value = {"/historyMatch/getTcMatch/{user_id}"}, method = RequestMethod.GET)
    public Integer getTcMatch(@PathVariable Integer user_id) {
        return historyMatchService.findTcMatch(user_id);
    }

    @RequestMapping(value = {"/historyMatch/getTbMatch/{user_id}"}, method = RequestMethod.GET)
    public Integer getTbMatch(@PathVariable Integer user_id) {
        return historyMatchService.findTbMatch(user_id);
    }

    @RequestMapping(value = {"/historyMatch/getTcOrderMatch/{football_id}"}, method = RequestMethod.GET)
    public Integer getTcOrderMatch(@PathVariable Integer football_id) {
        return historyMatchService.findTcOrderTeam(football_id);
    }

    @RequestMapping(value = {"/historyMatch/getTbOrderMatch/{football_id}"}, method = RequestMethod.GET)
    public Integer getTbOrderMatch(@PathVariable Integer football_id) {
        return historyMatchService.findTcOrderTeam(football_id);
    }

    @RequestMapping(value = {"/historyMatch/getListByUserName"}, method = RequestMethod.GET)
    public ResponseEntity<?> getListByUserName(HttpServletRequest httpServletRequest) {
        String jwt = cookieUtil.getValue(httpServletRequest, jwtTokenCookieName);
        System.out.println(jwt);
        if(null == jwt) {
            System.out.println("Chua login | khong the lay token trong cookie");
            // TODO return;
        }
        // kiem tra token duoc luu trong redis xem co hay khong
        // TODO
        // Neu dung thi tiep tuc
        String username = jwtUtil.getUsernameFromToken(jwt);
        try {
            return new ResponseEntity<>(historyMatchService.listHistoryMatchByUserName(username), HttpStatus.OK);
        }catch (Exception exception)
        {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
