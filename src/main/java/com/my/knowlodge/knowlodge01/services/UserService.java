package com.my.knowlodge.knowlodge01.services;

import com.my.knowlodge.knowlodge01.models.dto.PersonResponse;

import java.util.List;

public interface UserService {
    List<PersonResponse> listAll();
}
