package com.example.football_league.service;

import com.example.football_league.configuration.JwtUtil;
import com.example.football_league.exception.*;
import com.example.football_league.model.entity.UserApp;
import com.example.football_league.model.request.AuthRequest;
import com.example.football_league.model.response.AuthResponse;
import com.example.football_league.repository.UserAppRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthService {
    private final JwtUtil jwtUtil;
    private final AuthenticationManager authenticationManager;
    private final UserAppRepository userAppRepository;

    public AuthResponse authenticate(AuthRequest authRequest) {

        UserApp userApp = userAppRepository.getUserByEmail(authRequest.getEmail());
        System.out.println("Get User Mail : " + userApp);

        if (userApp == null) {
            throw new NotFoundExceptionHandler("User not found");
        } else if (authRequest.getEmail().isEmpty()) {
            throw new FieldEmptyExceptionHandler("Email field is empty");
        } else if (authRequest.getEmail().isBlank()) {
            throw new FieldBlankExceptionHandler("Email field is blank");
        } else if (!authRequest.getEmail().matches("^([a-zA-Z0-9_\\-\\.]+)@([a-zA-Z0-9_\\-]+)(\\.[a-zA-Z]{2,5}){1,2}$")) {
            throw new NotValidValueExceptionHandler("Email not valid");
        } else if (!userApp.getIsEnabled()) {
            throw new IsEnableExceptionHandler();
        }

        try {
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            authRequest.getEmail(),
                            authRequest.getPassword()
                    )
            );

            var user = userAppRepository.getUserByEmail(authRequest.getEmail());
            var jwtToken = jwtUtil.generateToken(user);

            return AuthResponse.builder()
                    .id(user.getId())
                    .email(user.getEmail())
                    .token(jwtToken)
                    .build();
        } catch (Exception e) {
            throw new PasswordNotMatchExceptionHandler("Password not match");
        }
    }
}
