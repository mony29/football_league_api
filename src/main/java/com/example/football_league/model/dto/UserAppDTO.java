package com.example.football_league.model.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserAppDTO {
    private int id;
    private String name;
//    private String password;
    private String email;
    private String profile;
    private Boolean isEnabled;
}
