package com.example.football.repositories;

import com.example.football.models.Caption;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CaptionRepository extends JpaRepository<Caption, Integer> {
}
