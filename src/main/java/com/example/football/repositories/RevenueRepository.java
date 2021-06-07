package com.example.football.repositories;

import com.example.football.models.Revenue;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface RevenueRepository extends JpaRepository<Revenue, Integer> {

    @Query(value = "SELECT * FROM revenue r \n" +
            "WHERE r.pitch_id=:pitch_id and r.number_pitch_id=:number_pitch_id \n"+
            "ORDER BY id ASC  ", nativeQuery = true)
    List<Revenue> findRevenue(@Param("pitch_id") Integer pitch_id, @Param("number_pitch_id") Integer number_pitch_id);

    @Query(value = "SELECT SUM(r.money_revenue) FROM revenue r\n" +
            "WHERE r.pitch_id=:pitch_id", nativeQuery = true)
    Integer sumRevenueAll(@Param("pitch_id") Integer pitch_id);
}
