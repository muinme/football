package com.example.football.models;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "dayofweek")
public class DayOfWeek {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String name;
}
