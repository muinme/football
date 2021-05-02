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
}
