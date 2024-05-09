package com.my.knowlodge.knowlodge01.security;

import com.my.knowlodge.knowlodge01.exceptions.infra.PersonNotFoundException;
import com.my.knowlodge.knowlodge01.models.AuthUserDetails;
import com.my.knowlodge.knowlodge01.repositories.PersonRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserDetailsServiceImpl implements UserDetailsService {
    private final PersonRepository personRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return this.personRepository.findPersonByEmail(username)
                .map(AuthUserDetails::new)
                .orElseThrow(PersonNotFoundException::new);
    }
}
