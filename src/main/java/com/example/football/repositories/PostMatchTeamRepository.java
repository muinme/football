package com.example.football.repositories;

import com.example.football.models.DetailPitch;
import com.example.football.models.PostMatchTeam;
import com.example.football.models.TeamFootBall;
import com.example.football.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import javax.persistence.criteria.CriteriaBuilder;
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

    @Query(value = "SELECT COUNT(*) FROM wait_match_team wmt\n" +
            "WHERE wmt.football_id =:football_id  AND wmt.status=\"1\"", nativeQuery = true)
    Integer findSlWaitPost(@Param("football_id") Integer id);

    @Query(value = "SELECT COUNT(*) FROM wait_match_team wmt\n" +
            "WHERE wmt.football_id =:football_id  AND wmt.status=\"0\"", nativeQuery = true)
    Integer findSlWaitPostDel(@Param("football_id") Integer id);

    @Query(value = "SELECT * FROM wait_match_team wmt \n" +
            "INNER JOIN request_match rm ON rm.wait_match_team_id = wmt.id \n" +
            "WHERE rm.id =:request_match_id", nativeQuery = true)
    PostMatchTeam getInFo(@Param("request_match_id") Integer request_match_id);

    @Query(value = "SELECT playtime FROM wait_match_team \n" +
            "GROUP BY playtime", nativeQuery = true)
    List<String> findPlayTime();

    @Query(value = "SELECT * FROM wait_match_team wmt \n" +
            "WHERE wmt.address LIKE :qh", nativeQuery = true)
    List<PostMatchTeam> findPostByAddress(@Param("qh") String qh);

    @Query(value = "SELECT * FROM wait_match_team wmt\n" +
            "WHERE wmt.levelwant LIKE :levelwant", nativeQuery = true)
    List<PostMatchTeam> findByLevel(@Param("levelwant") String levelwant);

    @Query(value = "SELECT * FROM wait_match_team wmt\n" +
            "WHERE wmt.playtime =:playtime", nativeQuery = true)
    List<PostMatchTeam> findByPlayTime(@Param("playtime") String playtime);

    @Query(value = "SELECT * FROM wait_match_team wmt\n" +
            "WHERE wmt.playtime =:playtime AND wmt.levelwant LIKE :levelwant", nativeQuery = true)
    List<PostMatchTeam> findByPlayTimeAndLevel(@Param("playtime") String playtime, @Param("levelwant") String levelwant);
}
