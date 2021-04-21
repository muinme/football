package com.example.football.models;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "request_pitch")
public class RequestPitch {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private Integer user_id;
    private Integer pitch_id;
    private Integer pitch_detail_id;
    private String status;
    private java.util.Date created;

}