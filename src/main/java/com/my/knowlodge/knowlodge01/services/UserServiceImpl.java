package com.my.knowlodge.knowlodge01.services;

import com.my.knowlodge.knowlodge01.models.Person;
import com.my.knowlodge.knowlodge01.repositories.PersonRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final PersonRepository personRepository;

    public Optional<Person> personByEmail(String email) {
        return this.personRepository.findPersonByEmail(email);
    }

}
