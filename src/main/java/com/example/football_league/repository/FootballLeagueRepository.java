package com.example.football_league.repository;

import com.example.football_league.model.entity.FootballLeague;
import com.example.football_league.model.request.FootballLeagueRequest;
import org.apache.ibatis.annotations.*;
//import org.mapstruct.Mapper;

@Mapper
public interface FootballLeagueRepository {
    @Results( id = "leagueMapper",
            value = {
                    @Result(column = "end_date", property = "endDate"),
                    @Result(column = "start_date", property = "startDate"),
                    @Result(column = "league_code", property = "leagueCode"),
                    @Result(column = "established_year", property = "establishedYear"),
                    @Result(column = "market_value", property = "marketValue")
            }
    )
    @Select("""
            select * from leagues where id = #{id}
            """)
    FootballLeague getById(Integer id);

    @Select("""
            insert into leagues(name, logo, league_code, country, season, start_date, end_date)
            values(#{req.name}, #{req.logo}, #{req.leagueCode}, #{req.country}, #{req.season},
            #{req.startDate}, #{req.endDate}) returning id
            """)
    Integer setupLeague(@Param("req") FootballLeagueRequest leagueRequest);


}
