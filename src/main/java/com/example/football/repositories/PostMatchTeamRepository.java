package com.example.football.repositories;

import com.example.football.models.PostMatchTeam;
import com.example.football.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface PostMatchTeamRepository extends JpaRepository<PostMatchTeam, Integer> {

    @Query(value = "SELECT * FROM wait_match_team w\n" +
            "WHERE w.status =\"1\"", nativeQuery = true)
    List<PostMatchTeam> findListPostMatchTeam();

    @Query(value = "SELECT * FROM wait_match_team wmt \n" +
            "INNER JOIN teamfootballs t ON t.id = wmt.football_id \n" +
            "INNER JOIN caption c ON c.football_id = t.id \n" +
            "INNER JOIN users u ON u.id = c.user_id \n" +
            "WHERE u.username =:username", nativeQuery = true)
    List<PostMatchTeam> findListByUsername(@Param("username") String username);
}
