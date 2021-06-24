package com.codecool.movingmotivators.service;

import com.codecool.movingmotivators.model.CardModel;
import com.codecool.movingmotivators.repository.CardRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CardService {
    @Autowired
    private CardRepository cardRepository;

    public List<CardModel> getAllCards() {
        return cardRepository.findAll();
    }
}
