package com.my.knowlodge.knowlodge01.services;

import com.my.knowlodge.knowlodge01.exceptions.infra.PersonExistException;
import com.my.knowlodge.knowlodge01.exceptions.infra.PersonNotFoundException;
import com.my.knowlodge.knowlodge01.exceptions.infra.PersonNotNullException;
import com.my.knowlodge.knowlodge01.exceptions.infra.PersonNotRegisteredException;
import com.my.knowlodge.knowlodge01.models.Person;
import com.my.knowlodge.knowlodge01.models.dto.AuthRequest;
import com.my.knowlodge.knowlodge01.models.dto.AuthResponse;
import com.my.knowlodge.knowlodge01.models.dto.PersonRequest;
import com.my.knowlodge.knowlodge01.models.enums.UserRoles;
import com.my.knowlodge.knowlodge01.repositories.PersonRepository;
import com.my.knowlodge.knowlodge01.security.JwtService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {
    private final AuthenticationManager authenticationManager;
    private final PersonRepository personRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;

    public Optional<Person> personByEmail(String email) {
        return this.personRepository.findPersonByEmail(email);
    }

    @Override
    public AuthResponse login(AuthRequest request) {
        if (request.email().isEmpty() || request.password().isEmpty()) throw new PersonNotNullException();
        if (personByEmail(request.email()).isEmpty()) throw new PersonNotFoundException();
        var auth = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(request.email(), request.password()));
        if (!auth.isAuthenticated()) throw new PersonNotNullException();
        return new AuthResponse(this.jwtService.generateToken(request.email()));
    }

    @Override
    public AuthResponse register(PersonRequest request) {
        if (request == null) throw new PersonNotNullException();
        if (personByEmail(request.email()).isPresent()) throw new PersonExistException();
        Person person = new Person(request.name(), request.email(), passwordEncoder.encode(request.password()), request.age(), UserRoles.USER);
        Person personSaved = this.personRepository.save(person);
        if (personSaved.getId() == null) throw new PersonNotRegisteredException();
        return new AuthResponse(this.jwtService.generateToken(request.email()));
    }
}


