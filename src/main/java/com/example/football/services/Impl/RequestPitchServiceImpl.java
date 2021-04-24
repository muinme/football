package com.example.football.services.Impl;

import com.example.football.infrastructure.security.JwtUtil;
import com.example.football.models.RequestMatch;
import com.example.football.models.RequestPitch;
import com.example.football.models.TeamFootBall;
import com.example.football.repositories.RequestMatchRepository;
import com.example.football.repositories.RequestPitchRepository;
import com.example.football.repositories.TeamFootBallRepository;
import com.example.football.services.RequestPitchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class RequestPitchServiceImpl implements RequestPitchService {

    @Autowired
    private RequestPitchRepository requestPitchRepository;
    @Override
    public RequestPitch createRequestPitch(RequestPitch requestPitch) {
        requestPitch.setCreated(new Date());
        return requestPitchRepository.save(requestPitch);
    }

    @Override
    public RequestPitch getByIdRequestPitch(Integer id) {
        return requestPitchRepository.findById(id).get();
    }

    @Override
    public void saveRequestPitch(RequestPitch requestPitch) {
        requestPitchRepository.save(requestPitch);
    }

    @Override
    public List<RequestPitch> listAllRequestPitch() {
        return requestPitchRepository.findAll();
    }

}
