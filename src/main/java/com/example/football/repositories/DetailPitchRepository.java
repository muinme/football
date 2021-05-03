package com.example.football.repositories;

import com.example.football.models.DetailPitch;
import com.example.football.models.PieInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface DetailPitchRepository extends JpaRepository<DetailPitch, Integer> {
    @Query(value = "SELECT dp.* FROM detail_pitchs dp \n" +
            "INNER JOIN inventory i ON number_pitch = dp.number_pitch_id \n" +
            "WHERE i.pitch_id =:pitch_id and dp.timeslot_id =:timeslot_id and dp.day_id=:day_id and dp.number_pitch_id=:number_pitch_id", nativeQuery = true)
     DetailPitch getDetailPitch(@Param("timeslot_id") Integer timeslot_id, @Param("pitch_id") Integer pitch_id, @Param("day_id") Integer day_id, @Param("number_pitch_id") Integer number_pitch_id);
    @Query(value = "SELECT dp.* FROM detail_pitchs dp \n" +
            "INNER JOIN inventory i ON number_pitch = dp.number_pitch_id \n" +
            "WHERE i.pitch_id =:pitch_id and dp.timeslot_id =:timeslot_id and dp.day_id=:day_id and dp.status_hire=:status_hire", nativeQuery = true)
    List<DetailPitch> getListDetailPitch(@Param("timeslot_id") Integer timeslot_id, @Param("pitch_id") Integer pitch_id, @Param("day_id") Integer day_id, @Param("status_hire") String status_hire);
}
