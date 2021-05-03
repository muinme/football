package com.example.football.repositories;

import com.example.football.models.DayOfWeek;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DayOfWeekRepository extends JpaRepository<DayOfWeek, Integer> {
}
