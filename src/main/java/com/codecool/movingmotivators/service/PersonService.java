package com.codecool.movingmotivators.service;

import com.codecool.movingmotivators.model.*;
import com.codecool.movingmotivators.repository.PersonRepository;
import com.codecool.movingmotivators.repository.QuestionGroupRepository;
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
    private final QuestionGroupService questionGroupService;
    private final QuestionService questionService;
    private final CardService cardService;
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

        QuestionGroup defaultQuestionGroup = QuestionGroup.builder()
                .person(person)
                .groupName("Default")
                .build();

        questionGroupService.addQuestionGroup(defaultQuestionGroup);

        Question defaultQuestion = Question.builder()
                .questionGroup(defaultQuestionGroup)
                .questionText("")
                .finalized(false)
                .build();

        questionService.addQuestion(defaultQuestion);

        Card curiosity = Card.builder()
                .cardType(CardType)
                .build();

        return ResponseEntity.ok().build();
    }

    public Optional<Person> getUser(String username) { return personRepository.findByUsername(username); }
}
