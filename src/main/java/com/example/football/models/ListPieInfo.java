package com.example.football.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.List;

@Data
public class ListPieInfo {
    @JsonProperty(value = "listPieInfo", required = true)
    private List<PieInfo> listPieInfo;
}
