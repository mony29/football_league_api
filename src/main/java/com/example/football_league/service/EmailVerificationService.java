package com.example.football_league.service;

import com.example.football_league.model.entity.EmailVerification;

public interface EmailVerificationService {
    EmailVerification getEmailVerificationByCode(String code);
}
