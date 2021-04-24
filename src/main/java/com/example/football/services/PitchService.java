package com.example.football.services;

import com.example.football.models.Pitch;
import java.util.List;

public interface PitchService {
        Pitch createPitch(Pitch pitch);

        Pitch getByIdPitch(Integer id);

        void savePitch(Pitch pitch);

        void deletePitch(Integer id);

        List<Pitch> listAllPitch();
}
