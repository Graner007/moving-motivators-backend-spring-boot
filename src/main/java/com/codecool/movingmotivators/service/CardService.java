package com.codecool.movingmotivators.service;

import com.codecool.movingmotivators.model.*;
import com.codecool.movingmotivators.repository.CardRepository;
import com.codecool.movingmotivators.repository.EmptyCardRepository;
import com.codecool.movingmotivators.security.jwt.JwtService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.List;

@Service
public class CardService {

    @Autowired
    private CardRepository cardRepository;

    @Autowired
    private EmptyCardRepository emptyCardRepository;

    @Autowired
    private JwtService jwtService;

    @Autowired
    private QuestionGroupService questionGroupService;

    @Autowired
    private QuestionService questionService;

    public Collection<Card> getAllCards() { return cardRepository.findAll(); }

    public Card createCard(int orderNumber, Question question, VerticalStatusName verticalStatusName, CardType cardType) {
        return Card.builder()
                .orderNumber(orderNumber)
                .question(question)
                .verticalStatusName(verticalStatusName)
                .cardType(cardType)
                .build();
    }

    public void addCards(List<Card> cards) { cardRepository.saveAll(cards); }

    public void addEmptyCards(List<EmptyCard> cards) { emptyCardRepository.saveAll(cards); }

    public Collection<Card> getCardsByQuestionId(long id) { return cardRepository.getCardsByQuestionId(id); }

    public EmptyCard createEmptyCard(Card card, VerticalStatusName verticalStatusName, CardType cardType) {
        return EmptyCard.builder()
                .card(card)
                .verticalStatusName(verticalStatusName)
                .cardType(cardType)
                .build();
    }

    public ResponseEntity getCardsByQuestionGroupNameAndQuestionName(String bearerToken, String questionGroupName, String questionName) {
        if (bearerToken != null) {
            String token = jwtService.getTokenWithoutBearer(bearerToken);
            Question question = questionService.getQuestionByName(questionName);

            return ResponseEntity.ok(cardRepository.getCardsByQuestionId(question.getId()));

        }

        return ResponseEntity.badRequest().body("Token can not be null!");
    }

    public ResponseEntity changeCardsOrder(String bearerToken, String questionGroupName, String questionName, String dragCardId, String dropCardId) {
        if (bearerToken != null) {
            Question question = questionService.getQuestionByName(questionName);
            Collection<Card> cards = cardRepository.getCardsByQuestionId(question.getId());

            Card dragCard = null;
            Card dropCard = null;

            for (Card card : cards) {
                if (card.getId() == Long.parseLong(dragCardId))
                    dragCard = card;
                else if (card.getId() == Long.parseLong(dropCardId))
                    dropCard = card;
            }

            if (dragCard != null && dropCard != null) {
                dragCard.setOrderNumber(dropCard.getOrderNumber());
                dropCard.setOrderNumber(dragCard.getOrderNumber());
            }

            return ResponseEntity.ok().body("TODO: return new state");

        }

        return ResponseEntity.badRequest().body("Token can not be null!");
    }
}
