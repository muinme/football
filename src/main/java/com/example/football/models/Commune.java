package com.example.football.models;


import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "commune")
public class Commune {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer code;
    private String name;
    private Integer district_code;
}