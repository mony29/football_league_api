package com.example.football_league.model.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class FootballLeagueDTO {
    private Integer id;
    private String name;
    private String logo;
    private String leagueCode;
    private String country;
    private String season;
    private String startDate;
    private String endDate;
    private Timestamp establishedYear;
    private String marketValue;
    private String description;
}
