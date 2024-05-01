package com.my.knowlodge.knowlodge01.controllers;

import com.my.knowlodge.knowlodge01.models.dto.AuthResponse;
import com.my.knowlodge.knowlodge01.models.dto.PersonRequest;
import com.my.knowlodge.knowlodge01.models.dto.PersonResponse;
import com.my.knowlodge.knowlodge01.services.PersonService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/")
@RequiredArgsConstructor
public class PersonController {
    private final PersonService personService;

    @PostMapping("/register")
    public ResponseEntity<AuthResponse> personRegister(@RequestBody PersonRequest request) {
        return new ResponseEntity<>(this.personService.register(request), HttpStatus.OK);
    }

    @GetMapping("/list")
    public ResponseEntity<List<PersonResponse>> personList() {
        return new ResponseEntity<>(this.personService.listAll(), HttpStatus.OK);
    }
}
