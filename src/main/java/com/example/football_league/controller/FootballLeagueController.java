package com.example.football_league.controller;

import com.example.football_league.model.request.FootballLeagueRequest;
import com.example.football_league.model.response.BodyResponse;
import com.example.football_league.service.LeagueService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/football-league")
@SecurityRequirement(name = "bearerAuth")
public class FootballLeagueController {
    private final LeagueService leagueService;
    @PostMapping
    public ResponseEntity<?> setupLeague(@RequestBody FootballLeagueRequest leagueRequest){
        Integer returnId = leagueService.setupLeague(leagueRequest);
        return BodyResponse.getBodyResponse(leagueService.getById(returnId));
    }
    @GetMapping("/{id}")
    public ResponseEntity<?> getById(Integer id){
        return BodyResponse.getBodyResponse(leagueService.getById(id));
    }
}
