package com.example.football_league.model.mapper;

import com.example.football_league.model.dto.UserAppDTO;
import com.example.football_league.model.entity.UserApp;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface UserAppMapper {
    UserAppMapper INSTANCE = Mappers.getMapper(UserAppMapper.class);
    UserAppDTO toUserAppDto(UserApp userApp);
}
