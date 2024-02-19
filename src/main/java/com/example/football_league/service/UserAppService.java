package com.example.football_league.service;


import com.example.football_league.model.dto.UserAppDTO;
import com.example.football_league.model.entity.UserApp;
import com.example.football_league.model.request.UserAppRequest;
import jakarta.mail.MessagingException;

public interface UserAppService {
    UserApp register(UserAppRequest userAppRequest) throws MessagingException;

    UserAppDTO getUserById(Integer userId);
}
