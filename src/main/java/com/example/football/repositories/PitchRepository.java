package com.example.football.repositories;

import com.example.football.models.Pitch;
import com.example.football.models.RequestMatch;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface PitchRepository extends JpaRepository<Pitch, Integer> {
    @Query(value = "SELECT * FROM pitchs p \n" +
            "INNER JOIN owner_pitch op ON op.pitch_id = p.id \n" +
            "INNER JOIN users u ON u.id = op.user_id \n" +
            "WHERE u.username =:username", nativeQuery = true)
    List<Pitch> findByUsername(@Param("username") String username);
}