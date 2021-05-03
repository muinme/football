package com.example.football.repositories;

import com.example.football.models.Price;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PriceRepository extends JpaRepository<Price, Integer> {

}
