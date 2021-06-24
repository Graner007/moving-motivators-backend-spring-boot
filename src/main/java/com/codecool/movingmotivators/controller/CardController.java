package com.codecool.movingmotivators.controller;

import com.codecool.movingmotivators.service.CardService;
import com.google.gson.Gson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/cards")
@CrossOrigin(origins = {"${cross.origin.port.number}"})
public class CardController {

    @Autowired
    private CardService cardService;

    @GetMapping()
    public ResponseEntity getAllCards() { return ResponseEntity.ok(cardService.getAllCards()); }
}
