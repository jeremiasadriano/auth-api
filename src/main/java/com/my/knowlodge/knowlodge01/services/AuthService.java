package com.my.knowlodge.knowlodge01.services;

import com.my.knowlodge.knowlodge01.models.dto.AuthRequest;
import com.my.knowlodge.knowlodge01.models.dto.AuthResponse;
import com.my.knowlodge.knowlodge01.models.dto.PersonRequest;

public interface AuthService {
    AuthResponse login(AuthRequest request);

    AuthResponse register(PersonRequest request);
}
