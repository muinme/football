package com.example.football.services;

import com.example.football.models.TeamFootBall;

import java.util.List;

public interface TeamFootBallService {
    TeamFootBall createTeamFootBall(TeamFootBall teamFootBall);

    TeamFootBall getByIdTeamFootBall(Integer id);

    void saveTeamFootBall(TeamFootBall teamFootBall);

    void deleteTeamFootBall(Integer id);

    List<TeamFootBall> listAllTeamFootBall();


}
