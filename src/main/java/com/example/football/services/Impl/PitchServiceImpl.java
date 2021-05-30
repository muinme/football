package com.example.football.services.Impl;

import com.example.football.infrastructure.security.JwtUtil;
import com.example.football.models.OwnerPitch;
import com.example.football.models.Pitch;
import com.example.football.repositories.OwnerPitchRepository;
import com.example.football.repositories.PitchRepository;
import com.example.football.repositories.UserRepository;
import com.example.football.services.PitchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.handler.UserRoleAuthorizationInterceptor;

import java.util.Date;
import java.util.List;

@Service
public class PitchServiceImpl implements PitchService {

    @Autowired
    private PitchRepository pitchRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private OwnerPitchRepository ownerPitchRepository;

    @Autowired
    private JwtUtil jwtUtil;

    @Autowired
    private AuthenticationServiceImpl authenticationService;

    @Override
    public Pitch createPitch(Pitch pitch, String username) {
        pitch.setCreated(new Date());
        pitchRepository.save(pitch);
        Integer user_id = userRepository.getIdByUserName(username);
        Integer id = pitchRepository.findIdMax();
        OwnerPitch ownerPitch = new OwnerPitch();
        ownerPitch.setPitch_id(id);
        ownerPitch.setUser_id(user_id);
        ownerPitch.setCreated(new Date());
        ownerPitchRepository.save(ownerPitch);
        return pitch;
    }


    @Override
    public Pitch getByIdPitch(Integer id) {
        return pitchRepository.findById(id).get();
    }

    @Override
    public void savePitch(Pitch pitch) {
        pitchRepository.save(pitch);
    }

    @Override
    public void deletePitch(Integer id) {
        pitchRepository.deleteById(id);
    }

    @Override
    public List<Pitch > listAllPitch() {
        return pitchRepository.findAll();
    }

    @Override
    public List<Pitch> getPitchByUsername(String username) {
        return pitchRepository.findByUsername(username);
    }

    @Override
    public Pitch findNamePitch(Integer request_pitch_id) {
        return pitchRepository.findNamePitch(request_pitch_id);
    }
}
