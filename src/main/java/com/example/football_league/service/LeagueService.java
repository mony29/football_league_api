package com.example.football_league.service;

import com.example.football_league.model.entity.FootballLeague;
import com.example.football_league.model.request.FootballLeagueRequest;

public interface LeagueService {
    Integer setupLeague(FootballLeagueRequest leagueRequest);
    FootballLeague getById(Integer id);
}
