package com.example.football_league.model.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class FootballLeagueRequest {
    private String name;
    private String logo;
    private String leagueCode;
    private String country;
    private String season;
    private Timestamp startDate;
    private Timestamp endDate;
}

