package com.example.football.services.Impl;

import com.example.football.infrastructure.security.JwtUtil;
import com.example.football.models.OwnerPitch;
import com.example.football.models.Pitch;
import com.example.football.models.TeamFootBall;
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
    public Pitch updateProfilePitch(Pitch pitch, Integer pitch_id) {
        Pitch existingPitch = pitchRepository.findById(pitch_id).get();
        existingPitch.setCreated(new Date());
        existingPitch.setAddress(pitch.getAddress());
        existingPitch.setGooglemap(pitch.getGooglemap());
        existingPitch.setType(pitch.getType());
        existingPitch.setEmail(pitch.getEmail());
        existingPitch.setPhone(pitch.getPhone());
        existingPitch.setIntroduce(pitch.getIntroduce());
        existingPitch.setFacebook(pitch.getFacebook());
        existingPitch.setName(pitch.getName());
        existingPitch.setIntroduce(pitch.getIntroduce());
        return pitchRepository.save(existingPitch);
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
    public List<Pitch> getPitchByAddress(String tt, String qh) {
        tt = convertString(tt);
        qh = convertString(qh);
        return pitchRepository.findPitchByAddress(qh);
    }

    @Override
    public Pitch findNamePitch(Integer request_pitch_id) {
        return pitchRepository.findNamePitch(request_pitch_id);
    }

    public String convertString(String m)
    {
        m = m.trim();
        String [] tt = m.split(" ");
        String tmp = "%" + tt[tt.length - 2] +" "+tt[tt.length-1] + "%";
        return tmp;
    }
}
