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

    public ResponseEntity register(String username, String password) {
        Person person = Person.builder()
                .username(username)
                .password(password)
                .registrationDate(LocalDate.now())
                .build();

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
                .groupName("Default")
                .person(person)
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

        cardService.addCards(List.of(curiosity, honor, acceptance, mastery, power, freedom, relatedness, order, goal, status));

        EmptyCard curiosityPositiveEmptyCard = cardService.createEmptyCard(curiosity, VerticalStatusName.POSITIVE, cardTypeService.getCardTypeById(11));
        EmptyCard curiosityNegativeEmptyCard = cardService.createEmptyCard(curiosity, VerticalStatusName.NEGATIVE, cardTypeService.getCardTypeById(11));

        EmptyCard honorPositiveEmptyCard = cardService.createEmptyCard(honor, VerticalStatusName.POSITIVE, cardTypeService.getCardTypeById(11));
        EmptyCard honorNegativeEmptyCard = cardService.createEmptyCard(honor, VerticalStatusName.NEGATIVE, cardTypeService.getCardTypeById(11));

        EmptyCard acceptancePositiveEmptyCard = cardService.createEmptyCard(acceptance, VerticalStatusName.POSITIVE, cardTypeService.getCardTypeById(11));
        EmptyCard acceptanceNegativeEmptyCard = cardService.createEmptyCard(acceptance, VerticalStatusName.NEGATIVE, cardTypeService.getCardTypeById(11));

        EmptyCard masteryPositiveEmptyCard = cardService.createEmptyCard(mastery, VerticalStatusName.POSITIVE, cardTypeService.getCardTypeById(11));
        EmptyCard masteryNegativeEmptyCard = cardService.createEmptyCard(mastery, VerticalStatusName.NEGATIVE, cardTypeService.getCardTypeById(11));

        EmptyCard powerPositiveEmptyCard = cardService.createEmptyCard(power, VerticalStatusName.POSITIVE, cardTypeService.getCardTypeById(11));
        EmptyCard powerNegativeEmptyCard = cardService.createEmptyCard(power, VerticalStatusName.NEGATIVE, cardTypeService.getCardTypeById(11));

        EmptyCard freedomPositiveEmptyCard = cardService.createEmptyCard(freedom, VerticalStatusName.POSITIVE, cardTypeService.getCardTypeById(11));
        EmptyCard freedomNegativeEmptyCard = cardService.createEmptyCard(freedom, VerticalStatusName.NEGATIVE, cardTypeService.getCardTypeById(11));

        EmptyCard relatednessPositiveEmptyCard = cardService.createEmptyCard(relatedness, VerticalStatusName.POSITIVE, cardTypeService.getCardTypeById(11));
        EmptyCard relatednessNegativeEmptyCard = cardService.createEmptyCard(relatedness, VerticalStatusName.NEGATIVE, cardTypeService.getCardTypeById(11));

        EmptyCard orderPositiveEmptyCard = cardService.createEmptyCard(order, VerticalStatusName.POSITIVE, cardTypeService.getCardTypeById(11));
        EmptyCard orderNegativeEmptyCard = cardService.createEmptyCard(order, VerticalStatusName.NEGATIVE, cardTypeService.getCardTypeById(11));

        EmptyCard goalPositiveEmptyCard = cardService.createEmptyCard(goal, VerticalStatusName.POSITIVE, cardTypeService.getCardTypeById(11));
        EmptyCard goalNegativeEmptyCard = cardService.createEmptyCard(goal, VerticalStatusName.NEGATIVE, cardTypeService.getCardTypeById(11));

        EmptyCard statusPositiveEmptyCard = cardService.createEmptyCard(status, VerticalStatusName.POSITIVE, cardTypeService.getCardTypeById(11));
        EmptyCard statusNegativeEmptyCard = cardService.createEmptyCard(status, VerticalStatusName.NEGATIVE, cardTypeService.getCardTypeById(11));

        cardService.addEmptyCards(List.of(curiosityPositiveEmptyCard, curiosityNegativeEmptyCard,
                honorPositiveEmptyCard, honorNegativeEmptyCard,
                acceptancePositiveEmptyCard, acceptanceNegativeEmptyCard,
                masteryPositiveEmptyCard, masteryNegativeEmptyCard,
                powerPositiveEmptyCard, powerNegativeEmptyCard,
                freedomPositiveEmptyCard, freedomNegativeEmptyCard,
                relatednessPositiveEmptyCard, relatednessNegativeEmptyCard,
                orderPositiveEmptyCard, orderNegativeEmptyCard,
                goalPositiveEmptyCard, goalNegativeEmptyCard,
                statusPositiveEmptyCard, statusNegativeEmptyCard));

        return ResponseEntity.ok().build();
    }

    public Optional<Person> getUser(String username) { return personRepository.findByUsername(username); }
}
