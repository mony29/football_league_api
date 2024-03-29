package com.example.football_league.controller;

import com.example.football_league.model.request.AuthRequest;
import com.example.football_league.model.request.UserAppRequest;
import com.example.football_league.model.response.BodyResponse;
import com.example.football_league.service.AuthService;
import com.example.football_league.service.EmailVerificationService;
import com.example.football_league.service.serviceImp.UserAppServiceImp;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.mail.MessagingException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/auth")
@SecurityRequirement(name = "bearerAuth")
public class AuthController {
    private final UserAppServiceImp userAppServiceImp;
    private final AuthService authService;
    private final EmailVerificationService emailVerificationService;

    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody UserAppRequest userAppRequest) throws MessagingException {
        return BodyResponse.getBodyResponse(userAppServiceImp.register(userAppRequest));
    }

    @GetMapping("/{userId}")
    public ResponseEntity<?> getUserById(@PathVariable("userId") Integer userId) {
        return BodyResponse.getBodyResponse(userAppServiceImp.getUserById(userId));
    }

    @GetMapping("/verify")
    public ResponseEntity<?> verifyEmail(@RequestParam("code") String code) {
        return BodyResponse.getBodyResponse(emailVerificationService.getEmailVerificationByCode(code));
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody AuthRequest authRequest) {
        return BodyResponse.getBodyResponse(authService.authenticate(authRequest));
    }
}
