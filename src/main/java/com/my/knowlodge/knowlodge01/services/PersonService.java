package com.my.knowlodge.knowlodge01.services;

import com.my.knowlodge.knowlodge01.models.dto.AuthResponse;
import com.my.knowlodge.knowlodge01.models.dto.PersonRequest;
import com.my.knowlodge.knowlodge01.models.dto.PersonResponse;

import java.util.List;

public interface PersonService {
    AuthResponse register(PersonRequest request);

    List<PersonResponse> listAll();
}
