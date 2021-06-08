package com.example.football.services;

import com.example.football.models.PostMatchTeam;
import com.example.football.models.TeamFootBall;

import java.util.List;

public interface PostMatchTeamService {

    PostMatchTeam createPostMatchTeam(PostMatchTeam postMatchTeam, String nameTeamFootBall);

    PostMatchTeam getByIdPostMatchTeam(Integer id);

    void savePostMatchTeam(PostMatchTeam postMatchTeam);

    PostMatchTeam deletePostMatchTeam(Integer id);

    List<PostMatchTeam> listAllPostMatchTeam();

    List<PostMatchTeam> listFindByUsername(String username);

    List<PostMatchTeam> listFindByFootBallId(Integer football_id);

    Integer getSlWaitPost(Integer football_id);

    List<String> findPlayTime();

    List<PostMatchTeam> getPostByAddress(String tt, String qh);

    List<PostMatchTeam> getByPlayTimeAndLevelWant(String actionTime, String level);

    Integer getSlWaitPostDel(Integer football_id);

    PostMatchTeam getInfo(Integer request_match_id);

}
