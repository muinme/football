package com.example.football.services;

import com.example.football.models.PostMatchTeam;
import java.util.List;

public interface PostMatchTeamService {

    PostMatchTeam createPostMatchTeam(PostMatchTeam postMatchTeam, String nameTeamFootBall);

    PostMatchTeam getByIdPostMatchTeam(Integer id);

    void savePostMatchTeam(PostMatchTeam postMatchTeam);

    void deletePostMatchTeam(Integer id);

    List<PostMatchTeam> listAllPostMatchTeam();

    List<PostMatchTeam> listFindByUsername(String username);

    Integer getSlWaitPost(Integer football_id);

    Integer getSlWaitPostDel(Integer football_id);

    PostMatchTeam getInfo(Integer request_match_id);

}
