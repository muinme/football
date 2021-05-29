package com.example.football.repositories;

import com.example.football.models.RequestMatch;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface RequestMatchRepository extends JpaRepository<RequestMatch, Integer>{
    @Query(value = "SELECT rm.* FROM request_match rm \n" +
            "           INNER JOIN wait_match_team wmt ON rm.wait_match_team_id = wmt.id \n" +
            "            INNER JOIN teamfootballs t ON t.id  = wmt.football_id\n" +
            "            INNER JOIN caption c ON c.football_id = t.id \n" +
            "            INNER JOIN users u ON u.id = c.user_id \n" +
            "            WHERE u.username =:username && rm.status = \"0\"", nativeQuery = true)
    List<RequestMatch> findByUsername(@Param("username") String username);

    @Query(value="SELECT COUNT(*) FROM request_match rm\n" +
            "WHERE user_id =:user_id AND status = \"0\"", nativeQuery = true)
    Integer findSlWait(@Param("user_id") Integer id);

    @Query(value = "SELECT COUNT(*) FROM request_match rm \n" +
            "INNER JOIN wait_match_team wmt ON wmt.id = rm.wait_match_team_id \n" +
            "WHERE wmt.football_id =:football_id AND rm.status=\"0\"", nativeQuery = true)
    Integer findSlWaitTeam(@Param("football_id") Integer id);
}
