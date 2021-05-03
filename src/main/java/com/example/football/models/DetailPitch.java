package com.example.football.models;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "detail_pitchs")
public class DetailPitch {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private Integer timeslot_id;
    private Integer day_id;
    private Integer price_id;
    private Integer count_id;
    private Integer type_pitch_id;
    private String status_hire;
    private Integer number_pitch_id;
    private java.util.Date last_update;
}