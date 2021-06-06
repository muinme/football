package com.example.football.services.Impl;

import com.example.football.infrastructure.security.JwtUtil;
import com.example.football.models.Caption;
import com.example.football.models.OwnerPitch;
import com.example.football.models.Pitch;
import com.example.football.models.TeamFootBall;
import com.example.football.repositories.CaptionRepository;
import com.example.football.repositories.OwnerPitchRepository;
import com.example.football.repositories.TeamFootBallRepository;
import com.example.football.repositories.UserRepository;
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
    private UserRepository userRepository;

    @Autowired
    private CaptionRepository captionRepository;

    @Autowired
    private AuthenticationServiceImpl authenticationService;

    @Override
    public TeamFootBall createTeamFootBall(TeamFootBall teamFootBall, String username) {
        teamFootBall.setCreated(new Date());
        teamFootBallRepository.save(teamFootBall);
        Integer user_id = userRepository.getIdByUserName(username);
        Integer id = teamFootBallRepository.findIdMax();
        Caption caption = new Caption();
        caption.setFootball_id(id);
        caption.setUser_id(user_id);
        caption.setCreated(new Date());
        captionRepository.save(caption);
        return teamFootBall;
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

    @Override
    public TeamFootBall findNameTeam(Integer football_id) {
        return teamFootBallRepository.findNameTeam(football_id);
    }

    @Override
    public List<TeamFootBall> getTeamByUsername(String username) {
        return teamFootBallRepository.findByUsername(username);
    }

    @Override
    public List<String> findActionTime() {
        return teamFootBallRepository.findActionTime();
    }

    @Override
    public List<TeamFootBall> getTeamByAddress(String tt, String qh) {
         tt = convertString(tt);
         qh = convertString(qh);
         return teamFootBallRepository.findTeamByAddress(qh);
    }

    @Override
    public List<TeamFootBall> getByActionTimeAndLevel(String actionTime, String level) {
        System.out.println(actionTime);
        System.out.println(level);

        if(actionTime.equals("All") && !level.equals("All")) {
            return teamFootBallRepository.findByLevel("%"+level+"%");
        }
        if(!actionTime.equals("All") && level.equals("All")) {
            return teamFootBallRepository.findByActionTime(actionTime);
        }
        if(!actionTime.equals("All") && !level.equals("All"))
        {
            return teamFootBallRepository.findByActionTimeAndLevel(actionTime,"%"+level+"%");
        }
        return teamFootBallRepository.findAll();
    }

    public String convertString(String m)
    {
        m = m.trim();
        String [] tt = m.split(" ");
        String tmp = "%" + tt[tt.length - 2] +" "+tt[tt.length-1] + "%";
        return tmp;
    }
}
