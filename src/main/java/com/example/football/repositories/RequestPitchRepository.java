package com.example.football.repositories;

import com.example.football.models.RequestMatch;
import com.example.football.models.RequestPitch;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface RequestPitchRepository extends JpaRepository<RequestPitch, Integer> {
    @Query(value = "SELECT rp.* FROM request_pitch rp \n" +
            "INNER JOIN pitchs p ON p.id = rp.pitch_id \n" +
            "INNER JOIN owner_pitch op ON op.pitch_id = p.id \n" +
            "INNER JOIN users u ON op.id = u.id \n" +
            "WHERE u.username =:username && rp.status = \"0\"", nativeQuery = true)
    RequestPitch findByUsername(@Param("username") String username);

}
