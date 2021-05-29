package com.example.football.repositories;

import com.example.football.models.RequestMatch;
import com.example.football.models.RequestPitch;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface RequestPitchRepository extends JpaRepository<RequestPitch, Integer> {
    @Query(value = "SELECT rp.* FROM request_pitch rp \n" +
            "INNER JOIN pitchs p ON p.id = rp.pitch_id \n" +
            "INNER JOIN owner_pitch op ON op.pitch_id = p.id \n" +
            "INNER JOIN users u ON op.user_id = u.id \n" +
            "WHERE u.username =:username && rp.status = \"0\"", nativeQuery = true)
    List<RequestPitch> findByUsername(@Param("username") String username);

    @Query(value = "SELECT COUNT(*) FROM request_pitch rp \n" +
            "WHERE user_id =:user_id AND status = \"0\"", nativeQuery = true)
    Integer findSlWait(@Param("user_id") Integer id);

    @Query(value = "SELECT COUNT(*) FROM request_pitch rp\n" +
            "WHERE rp.pitch_id =:pitch_id AND rp.status = \"0\"", nativeQuery = true)
    Integer findSlWaitPitch(@Param("pitch_id") Integer id);
}
