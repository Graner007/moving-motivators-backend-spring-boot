package com.codecool.movingmotivators.service;

import com.codecool.movingmotivators.model.Card;
import com.codecool.movingmotivators.model.CardType;
import com.codecool.movingmotivators.model.Question;
import com.codecool.movingmotivators.model.VerticalStatusName;
import com.codecool.movingmotivators.repository.CardRepository;
import com.codecool.movingmotivators.repository.EmptyCardRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CardService {

    @Autowired
    private CardRepository cardRepository;

    @Autowired
    EmptyCardRepository

    public List<Card> getAllCards() { return cardRepository.findAll(); }

    public Card createCard(int orderNumber, Question question, VerticalStatusName verticalStatusName, CardType cardType) {
        return Card.builder()
                .orderNumber(orderNumber)
                .question(question)
                .verticalStatusName(verticalStatusName)
                .cardType(cardType)
                .build();
    }

    public void addCards(List<Card> cards) { cardRepository.saveAll(cards); }
}
