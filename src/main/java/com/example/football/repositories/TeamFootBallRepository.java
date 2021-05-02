package com.example.football.repositories;

import com.example.football.models.TeamFootBall;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface TeamFootBallRepository extends JpaRepository<TeamFootBall, Integer> {
    @Query(value = "SELECT * FROM teamfootballs tf\n" +
            "WHERE tf.name =:nameTeamFootBall", nativeQuery = true)
    Integer findIdByNameTeamFootBall(@Param("nameTeamFootBall") String nameTeamFootBall);
}
