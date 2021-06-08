package com.example.football.services;

import com.example.football.models.Pitch;
import com.example.football.models.TeamFootBall;

import java.util.List;

public interface PitchService {
        Pitch createPitch(Pitch pitch, String username);

        Pitch updateProfilePitch(Pitch pitch, Integer pitch_id);

        Pitch updateLoadAvatarPitch(String url, Integer pitch_id);

        Pitch getByIdPitch(Integer id);

        void savePitch(Pitch pitch);

        void deletePitch(Integer id);

        List<Pitch> listAllPitch();

        List<Pitch> getPitchByUsername(String username);

        List<Pitch> getPitchByAddress(String tt, String qh);

        Pitch findNamePitch(Integer request_pitch_id);
}
