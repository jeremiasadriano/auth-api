package com.my.knowlodge.knowlodge01.services;

import com.my.knowlodge.knowlodge01.exceptions.infra.PersonExistException;
import com.my.knowlodge.knowlodge01.exceptions.infra.PersonNotFoundException;
import com.my.knowlodge.knowlodge01.exceptions.infra.PersonNotNullException;
import com.my.knowlodge.knowlodge01.exceptions.infra.PersonNotRegisteredException;
import com.my.knowlodge.knowlodge01.models.MailModel;
import com.my.knowlodge.knowlodge01.models.Person;
import com.my.knowlodge.knowlodge01.models.dto.AuthRequest;
import com.my.knowlodge.knowlodge01.models.dto.AuthResponse;
import com.my.knowlodge.knowlodge01.models.dto.PersonRequest;
import com.my.knowlodge.knowlodge01.models.enums.UserRoles;
import com.my.knowlodge.knowlodge01.repositories.PersonRepository;
import com.my.knowlodge.knowlodge01.security.JwtService;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {
    private static final Logger log = LoggerFactory.getLogger(AuthServiceImpl.class);
    private final AuthenticationManager authenticationManager;
    private final PersonRepository personRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final MailSenderService mailSenderService;

    public Optional<Person> personByEmail(String email) {
        return this.personRepository.findPersonByEmail(email);
    }

    @Override
    public AuthResponse login(AuthRequest request) {
        if (request.email().isEmpty() || request.password().isEmpty()) throw new PersonNotNullException();
        var auth = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(request.email(), request.password()));
        if (!auth.isAuthenticated()) throw new PersonNotFoundException();
        return new AuthResponse(this.jwtService.generateToken(request.email()));
    }

    @Override
    public AuthResponse register(PersonRequest request) {
        if (request.email().isEmpty() || request.password().isEmpty() || request.age() == null || request.name().isEmpty()) {
            throw new PersonNotNullException();
        }
        if (personByEmail(request.email()).isPresent()) throw new PersonExistException();
        Person person = new Person(request.name(), request.email(), passwordEncoder.encode(request.password()), request.age(), UserRoles.USER);
        Person personSaved = this.personRepository.save(person);
        if (personSaved.getId() == null) throw new PersonNotRegisteredException();
        var model = new MailModel(request.email(), "App Knowledge Registration", "Hi ".concat(request.name()).concat("! Now you should finish your registration, clicking in the link down below"), "");
        this.mailSenderService.registerConfirmation(model);
        return new AuthResponse(this.jwtService.generateToken(request.email()));
    }
}


