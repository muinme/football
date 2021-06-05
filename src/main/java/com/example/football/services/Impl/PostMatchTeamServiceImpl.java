package com.example.football.services.Impl;

import com.example.football.infrastructure.security.JwtUtil;
import com.example.football.models.PostMatchTeam;
import com.example.football.repositories.PostMatchTeamRepository;
import com.example.football.repositories.TeamFootBallRepository;
import com.example.football.services.PostMatchTeamService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class PostMatchTeamServiceImpl implements PostMatchTeamService {
    @Autowired
    private PostMatchTeamRepository postMatchTeamRepository;

    @Autowired
    private JwtUtil jwtUtil;

    @Autowired
    private AuthenticationServiceImpl authenticationService;

    @Autowired
    private TeamFootBallRepository teamFootBallRepository;

    @Override
    public PostMatchTeam createPostMatchTeam(PostMatchTeam postMatchTeam, String nameTeamFootBall) {
        postMatchTeam.setFootball_id(teamFootBallRepository.findIdByNameTeamFootBall(nameTeamFootBall));
        postMatchTeam.setCreated(new Date());
        return postMatchTeamRepository.save(postMatchTeam);
    }

    @Override
    public PostMatchTeam getByIdPostMatchTeam(Integer id) {
        return postMatchTeamRepository.findById(id).get();
    }

    @Override
    public void savePostMatchTeam(PostMatchTeam postMatchTeam) {
        postMatchTeamRepository.save(postMatchTeam);
    }

    @Override
    public PostMatchTeam deletePostMatchTeam(Integer id) {
        PostMatchTeam postMatchTeam = postMatchTeamRepository.findById(id).get();
        postMatchTeam.setStatus("0");
        return postMatchTeamRepository.save(postMatchTeam);
    }

    @Override
    public List<PostMatchTeam> listAllPostMatchTeam() {
        return postMatchTeamRepository.findListPostMatchTeam();
    }

    @Override
    public List<PostMatchTeam> listFindByUsername(String username) {
        return postMatchTeamRepository.findListByUsername(username);
    }

    @Override
    public Integer getSlWaitPost(Integer football_id) {
        return postMatchTeamRepository.findSlWaitPost(football_id);
    }

    @Override
    public Integer getSlWaitPostDel(Integer football_id) {
        return postMatchTeamRepository.findSlWaitPostDel(football_id);
    }

    @Override
    public PostMatchTeam getInfo(Integer request_match_id) {
        return postMatchTeamRepository.getInFo(request_match_id);
    }
}

