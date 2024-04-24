package com.my.knowlodge.knowlodge01.services;

import com.my.knowlodge.knowlodge01.models.dto.AuthResponse;
import com.my.knowlodge.knowlodge01.models.dto.PersonRequest;

public interface PersonService {
    AuthResponse register(PersonRequest request);
}
