package com.example.football.services.Impl;

import com.example.football.infrastructure.security.JwtUtil;
import com.example.football.models.Pitch;
import com.example.football.models.TeamFootBall;
import com.example.football.repositories.PitchRepository;
import com.example.football.repositories.TeamFootBallRepository;
import com.example.football.services.TeamFootBallService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class TeamFootBallServiceImpl implements TeamFootBallService {
    @Autowired
    private TeamFootBallRepository teamFootBallRepository;

    @Autowired
    private JwtUtil jwtUtil;

    @Autowired
    private AuthenticationServiceImpl authenticationService;

    @Override
    public TeamFootBall createTeamFootBall(TeamFootBall teamFootBall) {
        teamFootBall.setCreated(new Date());
        return teamFootBallRepository.save(teamFootBall);
    }

    @Override
    public TeamFootBall getByIdTeamFootBall(Integer id) {
        return teamFootBallRepository.findById(id).get();
    }

    @Override
    public void saveTeamFootBall(TeamFootBall teamFootBall) {
        teamFootBallRepository.save(teamFootBall);
    }

    @Override
    public void deleteTeamFootBall(Integer id) {
        teamFootBallRepository.deleteById(id);
    }

    @Override
    public List<TeamFootBall> listAllTeamFootBall() {
        return teamFootBallRepository.findAll();
    }
}
