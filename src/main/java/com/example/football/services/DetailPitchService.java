package com.example.football.services;

import com.example.football.models.DetailPitch;

import java.util.List;

public interface DetailPitchService {
    DetailPitch createDetailPitch(DetailPitch detailPitch);

    DetailPitch getByIdDetailPitch(Integer id);

    void saveDetailPitch(DetailPitch detailPitch);

    void deleteDetailPitch(Integer id);

    List<DetailPitch> listAllDetailPitch();
}
