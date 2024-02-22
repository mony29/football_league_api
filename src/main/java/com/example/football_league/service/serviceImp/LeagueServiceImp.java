package com.example.football_league.service.serviceImp;

import com.example.football_league.model.entity.FootballLeague;
import com.example.football_league.model.request.FootballLeagueRequest;
import com.example.football_league.repository.FootballLeagueRepository;
import com.example.football_league.service.LeagueService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class LeagueServiceImp implements LeagueService {
    private final FootballLeagueRepository leagueRepository;

    @Override
    public Integer setupLeague(FootballLeagueRequest leagueRequest) {
        return leagueRepository.setupLeague(leagueRequest);
    }

    @Override
    public FootballLeague getById(Integer id) {
        return leagueRepository.getById(id);
    }
}
