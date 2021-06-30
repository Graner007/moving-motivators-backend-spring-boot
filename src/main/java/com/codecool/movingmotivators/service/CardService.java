package com.codecool.movingmotivators.service;

import com.codecool.movingmotivators.model.*;
import com.codecool.movingmotivators.repository.CardRepository;
import com.codecool.movingmotivators.repository.EmptyCardRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.List;

@Service
public class CardService {

    @Autowired
    private CardRepository cardRepository;

    @Autowired
    private EmptyCardRepository emptyCardRepository;

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
}
