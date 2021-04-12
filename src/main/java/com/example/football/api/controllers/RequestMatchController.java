package com.example.football.api.controllers;

import com.example.football.models.HistoryMatch;
import com.example.football.services.HistoryMatchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/football")
public class RequestMatchController {
    @Autowired
    private HistoryMatchService historyMatchService;

    @RequestMapping(value = {"/requestMatch/getAll"}, method = RequestMethod.GET)
    @PreAuthorize("@appAuthorizer.authorize(authentication, 'getAll', '/football/requestMatch/getAll')")
    public List<HistoryMatch> list() {
        return historyMatchService.listAllHistoryMatch();
    }
}
