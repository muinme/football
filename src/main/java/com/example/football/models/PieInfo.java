package com.example.football.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class PieInfo {

    @JsonProperty("x")
    private String x;
    @JsonProperty("value")
    private long value;
}
