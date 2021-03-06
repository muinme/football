package com.example.football.services.Impl;

import com.example.football.infrastructure.security.JwtUtil;
import com.example.football.models.Pitch;
import com.example.football.models.PostMatchTeam;
import com.example.football.repositories.PitchRepository;
import com.example.football.repositories.PostMatchTeamRepository;
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

    @Override
    public PostMatchTeam createPostMatchTeam(PostMatchTeam postMatchTeam) {
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
    public void deletePostMatchTeam(Integer id) {
        postMatchTeamRepository.deleteById(id);
    }

    @Override
    public List<PostMatchTeam> listAllPostMatchTeam() {
        return postMatchTeamRepository.findAll();
    }
}
