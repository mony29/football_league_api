package com.example.football_league.service.serviceImp;

import com.example.football_league.model.entity.EmailVerification;
import com.example.football_league.repository.EmailVerificationRepository;
import com.example.football_league.repository.UserAppRepository;
import com.example.football_league.service.EmailVerificationService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class EmailVerificationServiceImp implements EmailVerificationService {
    private final EmailVerificationRepository emailVerificationRepository;
    private final UserAppRepository userAppRepository;

    @Override
    public EmailVerification getEmailVerificationByCode(String code) {
        EmailVerification getVerification = emailVerificationRepository.getEmailVerificationByCode(code);
        System.out.println(getVerification);

        if (!getVerification.getIsVerified()) {
            emailVerificationRepository.verifyCode(code);
            userAppRepository.enableUser(getVerification.getUserId());
        }
        return emailVerificationRepository.getEmailVerificationByCode(code);
    }
}
