package com.codecool.movingmotivators.security;

import com.codecool.movingmotivators.model.Person;
import com.codecool.movingmotivators.repository.PersonRepository;
import com.codecool.movingmotivators.repository.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class UserDetails implements UserDetailsService {

    @Autowired
    private PersonRepository personRepository;

    @Autowired
    private RoleRepository roleRepository;

    @Override
    public org.springframework.security.core.userdetails.UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Person person = personRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("Username: " + username + " not found"));
        List<GrantedAuthority> roles = roleRepository.findAllByPersonId(person.getId()).stream()
                .map(p -> new SimpleGrantedAuthority(p.getUserRole().toString())).collect(Collectors.toList());

        return new CustomUser(person.getId(), person.getUsername(), person.getPassword(), roles);
    }
}
