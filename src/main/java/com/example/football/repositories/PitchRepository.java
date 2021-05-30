package com.example.football.repositories;

import com.example.football.models.Pitch;
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

    @Query(value = "SELECT MAX(p.id) FROM pitchs p",nativeQuery = true)
    Integer findIdMax();

    @Query(value = "SELECT p.* FROM pitchs p \n" +
            "INNER JOIN request_pitch rp ON p.id = rp.pitch_id\n" +
            "WHERE rp.id =:request_pitch_id", nativeQuery = true)
    Pitch findNamePitch(@Param("request_pitch_id") Integer request_pitch_id);

}