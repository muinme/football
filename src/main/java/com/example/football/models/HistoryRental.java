package com.example.football.models;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "history_rental")
public class HistoryRental {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private Integer request_pitch_id;
    private String status;
    private java.util.Date created;
}
