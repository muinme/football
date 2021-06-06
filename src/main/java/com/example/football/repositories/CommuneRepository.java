package com.example.football.repositories;

import com.example.football.models.Commune;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CommuneRepository extends JpaRepository<Commune, Integer>

    {
}
