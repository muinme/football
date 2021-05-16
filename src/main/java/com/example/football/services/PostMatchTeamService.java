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
}
