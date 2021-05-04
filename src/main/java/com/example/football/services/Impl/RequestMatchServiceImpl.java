package com.example.football.services.Impl;

import com.example.football.models.RequestMatch;
import com.example.football.repositories.RequestMatchRepository;
import com.example.football.repositories.UserRepository;
import com.example.football.services.MailService;
import com.example.football.services.RequestMatchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class RequestMatchServiceImpl implements RequestMatchService {
    @Autowired
    private RequestMatchRepository requestMatchRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private MailService mailService;

    @Override
    public RequestMatch createRequestMatch(Integer id, String username) {
        RequestMatch requestMatch = new RequestMatch();
        Integer user_id = userRepository.getIdByUserName(username);
        System.out.println(username);
        requestMatch.setWait_match_team_id(id);
        requestMatch.setUser_id(user_id);
        requestMatch.setCreated(new Date());
        requestMatch.setStatus("0");
        mailService.sendEmailBatDoi(username, id);
        return requestMatchRepository.save(requestMatch);
    }

    @Override
    public RequestMatch getByIdRequestMatch(Integer id) {
        return requestMatchRepository.findById(id).get();
    }

    @Override
    public void saveRequestMatch(RequestMatch requestMatch) {
        requestMatchRepository.save(requestMatch);
    }

    @Override
    public List<RequestMatch> getRequestMatchByUsername(String username) {
        return requestMatchRepository.findByUsername(username);

    }

    @Override
    public List<RequestMatch> listAllRequestMatch() {
        return requestMatchRepository.findAll();
    }
}
