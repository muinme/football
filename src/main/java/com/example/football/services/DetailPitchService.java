package com.example.football.services;

import com.example.football.models.DetailPitch;
import com.example.football.models.PostMatchTeam;

import java.util.List;

public interface DetailPitchService {
    List<DetailPitch> createDetailPitch(Integer pitch_id);

    DetailPitch getByIdDetailPitch(Integer id);

    List<DetailPitch> getListDetailPitch(Integer pitch_id, Integer timeslot_id, Integer day_id);

    List<String> getListStatusHire(Integer pitch_id, Integer number_pitch_id);
    List<String> getListMoneyPitch(Integer pitch_id, Integer number_pitch_id);

    void updateDetailPitchOfTime(Integer pitch_id, Integer timeslot_id, Integer day_id, Integer number_pitch_id, String status_hire);

    void saveDetailPitch(DetailPitch detailPitch);

    void deleteDetailPitch(Integer id);

    List<DetailPitch> listAllDetailPitch();

    DetailPitch getInfo(Integer request_pitch_id);

    Integer getSLPitch(Integer pitch_id);

    DetailPitch updateDetailPitchMoney(Integer pitch_id, Integer timeslot_id, Integer day_id, Integer number_pitch_id, Integer money);
}
