package com.example.football.api.controllers;

import com.example.football.infrastructure.security.JwtUtil;
import com.example.football.models.HistoryMatch;
import com.example.football.services.AuthenticationService;
import com.example.football.services.HistoryMatchService;
import com.example.football.services.Impl.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/football")
public class HistoryMatchController {
    @Autowired
    private HistoryMatchService historyMatchService;

    @RequestMapping(value = {"/historyMatch/getAll"}, method = RequestMethod.GET)
    public List<HistoryMatch> list() {
        return historyMatchService.listAllHistoryMatch();
    }
}
