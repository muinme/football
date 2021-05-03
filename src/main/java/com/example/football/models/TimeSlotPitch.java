package com.example.football.models;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "timeslot_pitch")
public class TimeSlotPitch {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String timeslot_detail;
}
