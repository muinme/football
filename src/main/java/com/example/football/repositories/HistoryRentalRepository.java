package com.example.football.repositories;

import com.example.football.models.HistoryRental;
import com.example.football.models.PieInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface HistoryRentalRepository extends JpaRepository<HistoryRental, Integer> {

    @Query(value = "SELECT COUNT(*) value , status x FROM history_rental hr \n" +
            "GROUP BY status ", nativeQuery = true)
    List<PieInfo> getInfoHistoryRental();

    @Query(value = "SELECT COUNT(*) FROM history_rental hr \n" +
            "INNER JOIN request_pitch rp ON hr.request_pitch_id = rp.id\n" +
            "WHERE rp.user_id =:user_id AND hr.status = \"1\"", nativeQuery = true)
    Integer findTcOrder(@Param("user_id") Integer id);

    @Query(value = "SELECT COUNT(*) FROM history_rental hr \n" +
            "INNER JOIN request_pitch rp ON hr.request_pitch_id = rp.id\n" +
            "WHERE rp.user_id =:user_id AND hr.status = \"0\"", nativeQuery = true)
    Integer findTbOrder(@Param("user_id") Integer id);

    @Query(value = "SELECT COUNT(*) FROM history_rental hr\n" +
            "INNER JOIN request_pitch rp ON hr.request_pitch_id = rp.id\n" +
            "WHERE rp.pitch_id =:pitch_id AND hr.status = \"1\"", nativeQuery = true)
    Integer findTcOrderPitch(@Param("pitch_id") Integer id);

    @Query(value = "SELECT COUNT(*) FROM history_rental hr\n" +
            "INNER JOIN request_pitch rp ON hr.request_pitch_id = rp.id\n" +
            "WHERE rp.pitch_id =:pitch_id AND hr.status = \"0\"", nativeQuery = true)
    Integer findTbOrderPitch(@Param("pitch_id") Integer id);



}
