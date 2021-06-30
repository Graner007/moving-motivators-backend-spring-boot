package com.codecool.movingmotivators.service;

import com.codecool.movingmotivators.model.CardType;
import com.codecool.movingmotivators.repository.CardTypeRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class CardTypeService {

    private final CardTypeRepository cardTypeRepository;

    public CardType getCardTypeById(long id) { return cardTypeRepository.getById(id); }
}
