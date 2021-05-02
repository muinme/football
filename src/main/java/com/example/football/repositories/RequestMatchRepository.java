package com.example.football.repositories;

import com.example.football.models.RequestMatch;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface RequestMatchRepository extends JpaRepository<RequestMatch, Integer>{
    @Query(value = "SELECT rm.* FROM request_match rm \n" +
            "           INNER JOIN wait_match_team wmt ON rm.wait_match_team_id = wmt.id \n" +
            "            INNER JOIN teamfootballs t ON t.id  = wmt.football_id\n" +
            "            INNER JOIN caption c ON c.football_id = t.id \n" +
            "            INNER JOIN users u ON u.id = c.user_id \n" +
            "            WHERE u.username =:username && rm.status = \"0\"", nativeQuery = true)
    RequestMatch findByUsername(@Param("username") String username);


}
