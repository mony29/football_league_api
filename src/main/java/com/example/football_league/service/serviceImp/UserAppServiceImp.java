package com.example.football_league.service.serviceImp;

import com.example.football_league.exception.*;
import com.example.football_league.model.dto.UserAppDTO;
import com.example.football_league.model.entity.UserApp;
import com.example.football_league.model.mapper.UserAppMapper;
import com.example.football_league.model.request.UserAppRequest;
import com.example.football_league.repository.EmailVerificationRepository;
import com.example.football_league.repository.UserAppRepository;
import com.example.football_league.service.MailSenderService;
import com.example.football_league.service.UserAppService;
import jakarta.mail.MessagingException;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Random;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class UserAppServiceImp implements UserAppService, UserDetailsService {
    private final EmailVerificationRepository emailVerificationRepository;
    private final UserAppRepository userAppRepository;
    private final MailSenderService mailSenderService;
    private final BCryptPasswordEncoder passwordEncoder;
    private UserApp userApp = new UserApp();
    private final UserAppMapper userAppMapper;

    @Override
    public UserDetails loadUserByUsername(String userEmail) throws UsernameNotFoundException {
        UserApp userApp = userAppRepository.getUserByEmail(userEmail);
        System.out.println("UserApp : " + userApp);

        if (userApp == null) {
            throw new NotFoundExceptionHandler("User not found.");
        }
        return userApp;
    }

    public UserApp register(UserAppRequest userAppRequest) throws MessagingException {
        String email = userAppRepository.getEmail(userAppRequest.getEmail());
        System.out.println("Email should be null : " + email);

        // check email condition
        if (userAppRequest.getEmail().equalsIgnoreCase(email)) {
            throw new UserDuplicateExceptionHandler("User already registered");
        } else {
            validateUserAppRequest(userAppRequest);
        }

        userAppRequest.setPassword(passwordEncoder.encode(userAppRequest.getPassword()));
        userApp = userAppRepository.insertUser(userAppRequest);
        System.out.println("UserApp : " + userApp);

        Random random = new Random();
        int randomCode = random.nextInt(900000) + 100000;
        String code = String.valueOf(randomCode);
        System.out.println("Code : " + code);

        emailVerificationRepository.insertEmailVerification(userApp.getId(), code);

        mailSenderService.sendMail(userAppRequest, code);

        return userApp;
    }


    public UserAppDTO getUserById(Integer userId) {

        userApp = userAppRepository.getUserById(userId);
//        if (userApp == null) {
//            throw new NotFoundExceptionHandler("User not found");
//        }
        return userAppMapper.INSTANCE.toUserAppDto(userApp);
    }

    private void validateUserAppRequest(UserAppRequest userAppRequest) {
        if (userAppRequest.getEmail().isEmpty()) {
            throw new FieldEmptyExceptionHandler("Email field is empty");
        } else if (userAppRequest.getEmail().isBlank()) {
            throw new FieldBlankExceptionHandler("Email field is blank");
        } else if (userAppRequest.getPassword().isEmpty()) {
            throw new FieldEmptyExceptionHandler("Password field is empty");
        } else if (userAppRequest.getPassword().isBlank()) {
            throw new FieldBlankExceptionHandler("Password field is blank");
        } else if (!userAppRequest.getEmail().matches("^([a-zA-Z0-9_\\-\\.]+)@([a-zA-Z0-9_\\-]+)(\\.[a-zA-Z]{2,5}){1,2}$")) {
            throw new NotValidValueExceptionHandler("Email not valid");
        }
    }

}
