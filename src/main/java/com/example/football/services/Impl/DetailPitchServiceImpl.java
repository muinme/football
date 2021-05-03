package com.example.football.services.Impl;

import com.example.football.infrastructure.security.JwtUtil;
import com.example.football.models.DetailPitch;
import com.example.football.models.Pitch;
import com.example.football.repositories.DetailPitchRepository;
import com.example.football.repositories.PitchRepository;
import com.example.football.services.DetailPitchService;
import com.example.football.services.PitchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class DetailPitchServiceImpl implements DetailPitchService{

    @Autowired
    private DetailPitchRepository detailPitchRepository;

    @Autowired
    private JwtUtil jwtUtil;

    @Autowired
    private AuthenticationServiceImpl authenticationService;

    @Override
    public DetailPitch createDetailPitch(DetailPitch detailPitch) {

        detailPitch.setLast_update(new Date());
        return detailPitchRepository.save(detailPitch);
    }

    @Override
    public DetailPitch getByIdDetailPitch(Integer id) {
        return detailPitchRepository.findById(id).get();
    }


    @Override
    public List<DetailPitch> getListDetailPitch(Integer pitch_id, Integer timeslot_id, Integer day_id) {
        return detailPitchRepository.getListDetailPitch(timeslot_id, pitch_id, day_id,"0");
    }

    @Override
    public void updateDetailPitchOfTime(Integer pitch_id, Integer timeslot_id, Integer day_id, Integer number_pitch_id,String status_hire) {
        DetailPitch exsitDetailPitch = detailPitchRepository.getDetailPitch(timeslot_id, pitch_id, day_id, number_pitch_id);
        exsitDetailPitch.setStatus_hire(status_hire);
        System.out.println(exsitDetailPitch);
        detailPitchRepository.save(exsitDetailPitch);

    }

    @Override
    public void saveDetailPitch(DetailPitch detailPitch) {
        detailPitchRepository.save(detailPitch);
    }

    @Override
    public void deleteDetailPitch(Integer id) {
        detailPitchRepository.deleteById(id);
    }

    @Override
    public List<DetailPitch> listAllDetailPitch() {
        return detailPitchRepository.findAll();
    }
}
