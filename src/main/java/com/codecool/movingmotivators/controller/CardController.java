package com.codecool.movingmotivators.controller;

import com.codecool.movingmotivators.service.CardService;
import com.google.gson.Gson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CardController {
    @Autowired
    private CardService cardService;

    private Gson gson = new Gson();

    @GetMapping("/cards")
    public String getAllCards() {
        return gson.toJson(cardService.getAllCards());
    }
}
