package com.my.knowlodge.knowlodge01.controllers;

import com.my.knowlodge.knowlodge01.models.dto.AuthRequest;
import com.my.knowlodge.knowlodge01.models.dto.PersonRequest;
import com.my.knowlodge.knowlodge01.security.JwtFilter;
import com.my.knowlodge.knowlodge01.services.AuthService;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseCookie;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/")
@RequiredArgsConstructor
public class AuthController {
    private final AuthService authService;
    private final JwtFilter jwtFilter;
    @Value("${app.security.token_prefix}")
    private String TOKEN_PREFIX;
    @Value("${app.security.cookie_name}")
    private String COOKIE_NAME;


    @PostMapping("/login")
    public ResponseEntity<HttpStatus> login(@RequestBody AuthRequest request) {
        String token = this.authService.login(request).token();
        ResponseCookie cookie = ResponseCookie.from(COOKIE_NAME, TOKEN_PREFIX.concat(token))
                .httpOnly(true)
                .path("/")
                .maxAge(360)
                .secure(false)
                .build();

        return ResponseEntity.ok()
                .header(HttpHeaders.SET_COOKIE, cookie.toString())
                .build();
    }

    @PostMapping("/register")
    public ResponseEntity<HttpStatus> personRegister(@RequestBody PersonRequest request, HttpServletResponse response) {
        String token = this.authService.register(request).token();
        this.jwtFilter.createCookie(response, token);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }
}
