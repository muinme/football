package com.example.football.services;

import com.example.football.models.Pitch;
import com.example.football.models.TeamFootBall;

import java.util.List;

public interface TeamFootBallService {
    TeamFootBall createTeamFootBall(TeamFootBall teamFootBall, String username);

    TeamFootBall getByIdTeamFootBall(Integer id);

    void saveTeamFootBall(TeamFootBall teamFootBall);

    void deleteTeamFootBall(Integer id);

    List<TeamFootBall> listAllTeamFootBall();

    TeamFootBall findNameTeam(Integer football_id);

    List<TeamFootBall> getTeamByUsername(String username);

    List<String> findActionTime();

    List<TeamFootBall> getTeamByAddress(String tt, String qh);

    List<TeamFootBall> getByActionTimeAndLevel(String actionTime, String level);

}
