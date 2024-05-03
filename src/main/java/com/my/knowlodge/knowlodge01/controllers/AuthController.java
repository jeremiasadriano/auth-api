package com.my.knowlodge.knowlodge01.controllers;

import com.my.knowlodge.knowlodge01.models.dto.AuthRequest;
import com.my.knowlodge.knowlodge01.models.dto.AuthResponse;
import com.my.knowlodge.knowlodge01.models.dto.PersonRequest;
import com.my.knowlodge.knowlodge01.services.AuthService;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
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

    @PostMapping("/login")
    public ResponseEntity<AuthResponse> login(@RequestBody AuthRequest request) {
        return new ResponseEntity<>(this.authService.login(request), HttpStatus.OK);
    }

    @PostMapping("/register")
    public ResponseEntity<AuthResponse> personRegister(@RequestBody PersonRequest request, HttpServletResponse response) {
        String token = this.authService.register(request).token();
        Cookie jwtCookie = new Cookie("user_token", token);
        jwtCookie.setHttpOnly(true);
        jwtCookie.setSecure(true);
        jwtCookie.setMaxAge(3500);
        response.addCookie(jwtCookie);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }
}
