package com.codecool.movingmotivators.service;

import com.codecool.movingmotivators.model.Person;
import com.codecool.movingmotivators.model.UserRole;
import com.codecool.movingmotivators.model.UserRoleEnum;
import com.codecool.movingmotivators.repository.PersonRepository;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class PersonService {

    private final PersonRepository personRepository;
    private final PasswordEncoder bCryptPasswordEncoder;

    public ResponseEntity register(Person person) {
        if (person.getUsername() == null || person.getRegistrationDate() == null || person.getPassword() == null)
            return ResponseEntity.badRequest().body("User details can not be null!");

        boolean usernameExists = personRepository
                .findByUsername(person.getUsername())
                .isPresent();

        if (usernameExists)
            return ResponseEntity.badRequest().body("Username already taken!");

        String encodedPassword = bCryptPasswordEncoder.encode(person.getPassword());

        person.setPassword(encodedPassword);
        person.setRegistrationDate(LocalDate.now());
        UserRole role = new UserRole(person, UserRoleEnum.ROLE_USER);

        person.setRoles(List.of(role));

        personRepository.save(person);
        return ResponseEntity.ok().build();
    }

    public Optional<Person> getUser(String username) { return personRepository.findByUsername(username); }
}
