package com.example.football.services.Impl;

import com.example.football.models.RequestMatch;
import com.example.football.repositories.RequestMatchRepository;
import com.example.football.services.RequestMatchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class RequestMatchServiceImpl implements RequestMatchService {
    @Autowired
    private RequestMatchRepository requestMatchRepository;
    @Override
    public RequestMatch createRequestMatch(RequestMatch requestMatch) {
        requestMatch.setCreated(new Date());
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
    public List<RequestMatch> listAllRequestMatch() {
        return requestMatchRepository.findAll();
    }
}
