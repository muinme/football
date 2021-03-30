package com.example.football.repositories;

import com.example.football.models.DetailPitch;
import com.example.football.models.Pitch;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DetailPitchRepository extends JpaRepository<DetailPitch, Integer> {
}
