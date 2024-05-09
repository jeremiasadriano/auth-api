package com.my.knowlodge.knowlodge01.controllers;

import com.my.knowlodge.knowlodge01.models.dto.AuthRequest;
import com.my.knowlodge.knowlodge01.models.dto.AuthResponse;
import com.my.knowlodge.knowlodge01.models.dto.EmailModel;
import com.my.knowlodge.knowlodge01.models.dto.PersonRequest;
import com.my.knowlodge.knowlodge01.services.AuthService;
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
    public ResponseEntity<AuthResponse> personRegister(@RequestBody PersonRequest request) {
        return new ResponseEntity<>(this.authService.register(request), HttpStatus.CREATED);
    }
}
