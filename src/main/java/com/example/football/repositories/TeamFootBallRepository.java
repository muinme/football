package com.example.football.repositories;

import com.example.football.models.Pitch;
import com.example.football.models.TeamFootBall;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface TeamFootBallRepository extends JpaRepository<TeamFootBall, Integer> {
    @Query(value = "SELECT * FROM teamfootballs tf\n" +
            "WHERE tf.name =:nameTeamFootBall", nativeQuery = true)
    Integer findIdByNameTeamFootBall(@Param("nameTeamFootBall") String nameTeamFootBall);

    @Query(value = "SELECT * FROM teamfootballs t \n" +
            "INNER JOIN caption c ON c.football_id = t.id \n" +
            "INNER JOIN users u ON u.id = c.user_id \n" +
            "WHERE u.username =:username", nativeQuery = true)
    List<TeamFootBall> findByUsername(@Param("username") String username);

    @Query(value = "SELECT MAX(t.id) FROM teamfootballs t ",nativeQuery = true)
    Integer findIdMax();
}
