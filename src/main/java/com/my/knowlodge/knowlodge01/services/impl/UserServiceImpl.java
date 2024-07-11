package com.my.knowlodge.knowlodge01.services.impl;

import com.my.knowlodge.knowlodge01.models.Person;
import com.my.knowlodge.knowlodge01.models.dto.PersonResponse;
import com.my.knowlodge.knowlodge01.repositories.PersonRepository;
import com.my.knowlodge.knowlodge01.services.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final PersonRepository personRepository;

    public Optional<Person> personByEmail(String email) {
        return this.personRepository.findPersonByEmail(email);
    }

    @Override
    public List<PersonResponse> list() {
        return this.personRepository.findAll()
                .stream()
                .map(PersonResponse::convert)
                .toList();
    }
}
