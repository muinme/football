package com.example.football.repositories;

import com.example.football.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface UserRepository extends JpaRepository<User, Integer> {
    @Query(value = "SELECT * FROM users u \n" +
            "WHERE u.username = :username", nativeQuery = true)
    User findByUsername(@Param("username") String username);

    @Query(value = "SELECT u.* FROM users u\n" +
            "INNER JOIN owner_pitch op ON op.user_id = u.id \n" +
            "WHERE op.pitch_id =:id", nativeQuery = true)
    User findByPitchId(@Param("id") String id);

    @Query(value = " SELECT u.* FROM users u\n" +
            "    INNER JOIN caption c ON u.id  = c.user_id\n" +
            "    WHERE c.football_id = :id", nativeQuery = true)
    User findByFootBallId(@Param("id") String id);

    @Query(value = "SELECT u.id FROM users u\n" +
            "WHERE u.username = :username", nativeQuery = true)
    Integer getIdByUserName(@Param("username") String username);

    @Query(value = "SELECT u.* FROM users u\n" +
            "INNER JOIN caption c ON u.id = c.user_id \n" +
            "INNER JOIN teamfootballs t ON t.id = c.football_id \n" +
            "INNER JOIN wait_match_team wm ON wm.football_id = t.id \n" +
            "WHERE wm.id =:id", nativeQuery = true)
    User findByPostId(@Param("id") Integer id);
}
