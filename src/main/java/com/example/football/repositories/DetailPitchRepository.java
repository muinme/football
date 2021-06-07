package com.example.football.repositories;

import com.example.football.models.DetailPitch;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface DetailPitchRepository extends JpaRepository<DetailPitch, Integer> {
    @Query(value = "SELECT dp.* FROM detail_pitchs dp \n" +
            "INNER JOIN inventory i ON number_pitch = dp.number_pitch_id \n" +
            "WHERE i.pitch_id =:pitch_id and dp.timeslot_id =:timeslot_id and dp.day_id=:day_id and dp.number_pitch_id=:number_pitch_id and dp.pitch_id=:pitch_id", nativeQuery = true)
    DetailPitch getDetailPitch(@Param("timeslot_id") Integer timeslot_id, @Param("pitch_id") Integer pitch_id, @Param("day_id") Integer day_id, @Param("number_pitch_id") Integer number_pitch_id);

    @Query(value = "SELECT dp.* FROM detail_pitchs dp \n" +
            "INNER JOIN inventory i ON number_pitch = dp.number_pitch_id \n" +
            "WHERE i.pitch_id =:pitch_id and dp.timeslot_id =:timeslot_id and dp.day_id=:day_id and dp.status_hire=:status_hire and dp.pitch_id=:pitch_id", nativeQuery = true)
    List<DetailPitch> getListDetailPitch(@Param("timeslot_id") Integer timeslot_id, @Param("pitch_id") Integer pitch_id, @Param("day_id") Integer day_id, @Param("status_hire") String status_hire);

    @Query(value = "SELECT dp.status_hire FROM detail_pitchs dp\n" +
            "        INNER JOIN inventory i ON number_pitch = dp.number_pitch_id\n" +
            "            WHERE i.pitch_id =:pitch_id\n" +
            "            and dp.number_pitch_id=:number_pitch_id and dp.pitch_id=:pitch_id", nativeQuery = true)
    List<String> getListStatusHire(@Param("pitch_id") Integer pitch_id, @Param("number_pitch_id") Integer number_pitch_id);

    @Query(value = "SELECT pp.price FROM detail_pitchs dp\n" +
            "INNER JOIN inventory i ON number_pitch = dp.number_pitch_id\n" +
            "INNER JOIN price_pitch pp ON pp.id = dp.price_id \n" +
            "WHERE i.pitch_id =:pitch_id\n" +
            "and dp.number_pitch_id=:number_pitch_id and dp.pitch_id=:pitch_id", nativeQuery = true)
    List<String> getListMoneyPitch(@Param("pitch_id") Integer pitch_id, @Param("number_pitch_id") Integer number_pitch_id);

    @Query(value = "SELECT dp.* FROM detail_pitchs dp \n" +
            "INNER JOIN request_pitch rp ON dp.id = rp.pitch_detail_id \n" +
            "WHERE rp.id =:request_pitch_id", nativeQuery = true)
    DetailPitch getInFo(@Param("request_pitch_id") Integer request_pitch_id);

    @Query(value = "SELECT SUM(pp.price) FROM detail_pitchs dp \n" +
            "INNER JOIN price_pitch pp ON dp.price_id = pp.id \n" +
            "WHERE dp.status_hire = \"1\" and dp.pitch_id=:pitch_id and dp.number_pitch_id=:number_pitch_id ", nativeQuery = true)
    Integer sumRevenue(@Param("pitch_id") Integer pitch_id, @Param("number_pitch_id") Integer number_pitch_id);

    @Query(value = "SELECT SUM(pp.price) FROM detail_pitchs dp \n" +
            "INNER JOIN price_pitch pp ON dp.price_id = pp.id \n" +
            "WHERE dp.status_hire = \"1\" and dp.pitch_id=:pitch_id ", nativeQuery = true)
    Integer sumRevenuePitch(@Param("pitch_id") Integer pitch_id);
}