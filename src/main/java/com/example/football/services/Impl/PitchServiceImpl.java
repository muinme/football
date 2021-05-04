package com.example.football.services.Impl;

import com.example.football.infrastructure.security.JwtUtil;
import com.example.football.models.Pitch;
import com.example.football.repositories.PitchRepository;
import com.example.football.services.PitchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class PitchServiceImpl implements PitchService {

    @Autowired
    private PitchRepository pitchRepository;

    @Autowired
    private JwtUtil jwtUtil;

    @Autowired
    private AuthenticationServiceImpl authenticationService;

    @Override
    public Pitch createPitch(Pitch pitch) {
        pitch.setCreated(new Date());
        return pitchRepository.save(pitch);
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
}
