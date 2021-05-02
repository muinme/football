package com.example.football.repositories;

import com.example.football.models.HistoryMatch;
import com.example.football.models.PieInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface HistoryMatchRepository extends JpaRepository<HistoryMatch, Integer> {
    @Query(value = "SELECT COUNT(*) value , status x FROM history_match hm \n" +
            "GROUP BY status ", nativeQuery = true)
    List<PieInfo> getInfoHistoryMatch();



}
