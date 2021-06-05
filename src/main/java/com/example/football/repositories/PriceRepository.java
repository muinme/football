package com.example.football.repositories;

import com.example.football.models.Price;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface PriceRepository extends JpaRepository<Price, Integer> {
    @Query(value = "SELECT pp.id FROM price_pitch pp \n" +
            "WHERE pp.price =:price", nativeQuery = true)
    Integer findByIdMoney(@Param("price") Integer price);
}
