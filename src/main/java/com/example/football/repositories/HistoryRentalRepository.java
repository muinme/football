package com.example.football.repositories;

import com.example.football.models.HistoryRental;
import com.example.football.models.PieInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface HistoryRentalRepository extends JpaRepository<HistoryRental, Integer> {
    @Query(value = "SELECT COUNT(*) value , status x FROM history_rental hr \n" +
            "GROUP BY status ", nativeQuery = true)
    List<PieInfo> getInfoHistoryRental();
}
