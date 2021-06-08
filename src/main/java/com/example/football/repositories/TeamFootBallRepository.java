package com.example.football.repositories;

import com.example.football.models.TeamFootBall;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import javax.transaction.Transactional;
import java.util.List;

@Transactional
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

    @Query(value = "SELECT t.* FROM  teamfootballs t \n" +
            "WHERE t.id =:football_id", nativeQuery = true)
    TeamFootBall findNameTeam(@Param("football_id") Integer football_id);

    @Query(value = "SELECT * FROM teamfootballs t \n" +
            "WHERE t.address LIKE :qh", nativeQuery = true)
    List<TeamFootBall> findTeamByAddress(@Param("qh") String qh);

    @Query(value = "SELECT actiontime FROM teamfootballs \n" +
            "GROUP BY actiontime", nativeQuery = true)
    List<String> findActionTime();

    @Query(value = "SELECT * FROM teamfootballs t\n" +
            "WHERE t.level LIKE :level", nativeQuery = true)
    List<TeamFootBall> findByLevel(@Param("level") String level);

    @Query(value = "SELECT * FROM teamfootballs t\n" +
            "WHERE t.actiontime =:actiontime", nativeQuery = true)
    List<TeamFootBall> findByActionTime(@Param("actiontime") String actiontime);

    @Query(value = "SELECT * FROM teamfootballs t\n" +
            "WHERE t.actiontime =:actiontime AND t.level LIKE :level", nativeQuery = true)
    List<TeamFootBall> findByActionTimeAndLevel(@Param("actiontime") String actiontime, @Param("level") String level);

    @Query(value = "SELECT COUNT(*) FROM teamfootballs t\n" +
            "            INNER JOIN caption c ON c.football_id = t.id\n" +
            "            INNER JOIN users u ON u.id = c.user_id\n" +
            "           WHERE u.username =:username", nativeQuery = true)
    Integer findSlTeam(@Param("username") String username);
}
