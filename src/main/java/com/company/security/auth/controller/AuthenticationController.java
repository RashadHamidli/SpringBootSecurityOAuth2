package com.company.security.auth.controller;

import com.company.security.auth.service.AuthenticationService;
import com.company.security.confirmationToken.ConfirmationTokenRequest;
import com.company.security.email.EmailSenderService;
import com.company.security.user.dto.RefreshTokenRequest;
import com.company.security.user.dto.RefreshTokenResponse;
import com.company.security.auth.dto.AuthenticationRequest;
import com.company.security.auth.dto.AuthenticationResponse;
import com.company.security.auth.dto.RegisterRequest;
import com.company.security.auth.dto.SuccessResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor
public class AuthenticationController {
    private final AuthenticationService authenticationService;
    private final static Logger LOGGER = LoggerFactory
            .getLogger(EmailSenderService.class);

    @PostMapping("/register")
    public ResponseEntity<SuccessResponse> register(
            @Valid @RequestBody RegisterRequest request
    ) {
        LOGGER.info("Register User");
        return ResponseEntity.ok(authenticationService.register(request));
    }

    @PostMapping("/confirm")
    public ResponseEntity<SuccessResponse> confirm(
            @Valid @RequestBody ConfirmationTokenRequest request
            ) {
        LOGGER.info("Confirm Token");
        return ResponseEntity.ok(authenticationService.confirmToken(request.getToken()));
    }

    @PostMapping("/login")
    public ResponseEntity<AuthenticationResponse> register(
            @Valid @RequestBody AuthenticationRequest request
    ) {
        LOGGER.info("Login User");
        return ResponseEntity.ok(authenticationService.authenticate(request));
    }

    @PostMapping("/logout")
    public ResponseEntity<String> logout(
            @Valid @RequestBody RefreshTokenRequest request
    ) {
        LOGGER.info("Logout User");
        authenticationService.logout(request);
        return ResponseEntity.ok("Logout successfully");
    }

    @PostMapping("/logout-all")
    public ResponseEntity<String> logoutAll(
            @Valid @RequestBody RefreshTokenRequest request
    ) {
        LOGGER.info("Logout All User");
        authenticationService.logoutAll(request);
        return ResponseEntity.ok("Logout successfully");
    }

    @PostMapping("/refresh-token")
    public ResponseEntity<RefreshTokenResponse> refreshToken(
            @Valid @RequestBody RefreshTokenRequest request
    ) {
        LOGGER.info("Refresh Token");
        return ResponseEntity.ok(authenticationService.refreshToken(request));
    }
}
