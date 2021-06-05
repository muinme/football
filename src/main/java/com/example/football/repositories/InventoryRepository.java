package com.example.football.repositories;

import com.example.football.models.Inventory;
import com.example.football.models.PieInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface InventoryRepository extends JpaRepository<Inventory, Integer> {
    @Query(value = "SELECT MAX(i.number_pitch) FROM detail_pitchs dp \n" +
            "INNER JOIN inventory i ON number_pitch = dp.number_pitch_id \n" +
            "WHERE i.pitch_id = :pitch_id", nativeQuery = true)
    Integer getMaxPitch(@Param("pitch_id") Integer pitch_id);

    @Query(value = "SELECT COUNT(i.number_pitch) FROM inventory i \n" +
            "WHERE i.pitch_id =:pitch_id", nativeQuery = true)
    Integer getSLPitch(@Param("pitch_id") Integer pitch_id);
}
