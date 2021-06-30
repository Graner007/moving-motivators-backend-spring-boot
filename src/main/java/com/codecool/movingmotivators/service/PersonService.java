package com.codecool.movingmotivators.service;

import com.codecool.movingmotivators.model.*;
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
    private final QuestionGroupService questionGroupService;
    private final QuestionService questionService;
    private final CardService cardService;
    private final CardTypeService cardTypeService;
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

        Card curiosity = cardService.createCard(1, defaultQuestion, VerticalStatusName.NEUTRAL, cardTypeService.getCardTypeById(1));
        Card honor = cardService.createCard(2, defaultQuestion, VerticalStatusName.NEUTRAL, cardTypeService.getCardTypeById(2));
        Card acceptance = cardService.createCard(3, defaultQuestion, VerticalStatusName.NEUTRAL, cardTypeService.getCardTypeById(3));
        Card mastery = cardService.createCard(4, defaultQuestion, VerticalStatusName.NEUTRAL, cardTypeService.getCardTypeById(4));
        Card power = cardService.createCard(5, defaultQuestion, VerticalStatusName.NEUTRAL, cardTypeService.getCardTypeById(5));
        Card freedom = cardService.createCard(6, defaultQuestion, VerticalStatusName.NEUTRAL, cardTypeService.getCardTypeById(6));
        Card relatedness = cardService.createCard(7, defaultQuestion, VerticalStatusName.NEUTRAL, cardTypeService.getCardTypeById(7));
        Card order = cardService.createCard(8, defaultQuestion, VerticalStatusName.NEUTRAL, cardTypeService.getCardTypeById(8));
        Card goal = cardService.createCard(9, defaultQuestion, VerticalStatusName.NEUTRAL, cardTypeService.getCardTypeById(9));
        Card status = cardService.createCard(10, defaultQuestion, VerticalStatusName.NEUTRAL, cardTypeService.getCardTypeById(10));
        Card defaultImage = cardService.createCard(11, defaultQuestion, VerticalStatusName.NEUTRAL, cardTypeService.getCardTypeById(11));

        cardService.addCards(List.of(curiosity, honor, acceptance, mastery, power, freedom, relatedness, order, goal, status, defaultImage));

        return ResponseEntity.ok().build();
    }

    public Optional<Person> getUser(String username) { return personRepository.findByUsername(username); }
}
