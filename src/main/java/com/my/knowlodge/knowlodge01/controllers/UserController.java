package com.my.knowlodge.knowlodge01.controllers;

import com.my.knowlodge.knowlodge01.models.dto.PersonResponse;
import com.my.knowlodge.knowlodge01.services.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/")
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;

    @GetMapping("/list")
    public ResponseEntity<List<PersonResponse>> personList() {
        return new ResponseEntity<>(this.userService.listAll(), HttpStatus.OK);
    }
}
