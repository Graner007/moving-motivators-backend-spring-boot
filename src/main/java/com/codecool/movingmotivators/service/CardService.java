package com.codecool.movingmotivators.service;

import com.codecool.movingmotivators.model.Card;
import com.codecool.movingmotivators.model.CardType;
import com.codecool.movingmotivators.model.Question;
import com.codecool.movingmotivators.model.VerticalStatusName;
import com.codecool.movingmotivators.repository.CardRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.List;

@Service
public class CardService {

    @Autowired
    private CardRepository cardRepository;

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

    public Collection<Card> getCardsByQuestionId(long id) { return cardRepository.getCardsByQuestionId(id); }
}
