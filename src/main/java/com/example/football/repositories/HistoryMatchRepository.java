package com.example.football.repositories;

import com.example.football.models.HistoryMatch;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface HistoryMatchRepository extends JpaRepository<HistoryMatch, Integer> {

    @Query(value = "SELECT COUNT(*) FROM history_match hm \n" +
            "INNER JOIN request_match rm ON hm.request_match_id = rm.id \n" +
            "WHERE rm.user_id =:user_id AND hm.status = \"1\"", nativeQuery = true)
    Integer findTcMatch(@Param("user_id") Integer id);

    @Query(value = "SELECT COUNT(*) FROM history_match hm \n" +
            "INNER JOIN request_match rm ON hm.request_match_id = rm.id \n" +
            "WHERE rm.user_id =:user_id AND hm.status = \"0\"", nativeQuery = true)
    Integer findTbMatch(@Param("user_id") Integer id);

    @Query(value = "SELECT COUNT(*) FROM history_match hm\n" +
            "INNER JOIN request_match rm ON hm.request_match_id = rm.id \n" +
            "INNER JOIN wait_match_team wmt ON wmt.id = rm.wait_match_team_id \n" +
            "WHERE wmt.football_id = 1 AND hm.status = \"1\"", nativeQuery = true)
    Integer findTcOrderMatch(@Param("pitch_id") Integer id);

    @Query(value = "SELECT COUNT(*) FROM history_match hm\n" +
            "INNER JOIN request_match rm ON hm.request_match_id = rm.id \n" +
            "INNER JOIN wait_match_team wmt ON wmt.id = rm.wait_match_team_id \n" +
            "WHERE wmt.football_id = 1 AND hm.status = \"0\"", nativeQuery = true)
    Integer findTbOrderMatch(@Param("pitch_id") Integer id);

}
