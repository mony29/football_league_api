package com.example.football_league.model.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class EmailVerification {
    private Integer id;
    private Integer userId;
    private String code;
    private Boolean isVerified;
}
