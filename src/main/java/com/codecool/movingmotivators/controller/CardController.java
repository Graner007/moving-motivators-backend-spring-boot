package com.codecool.movingmotivators.controller;

import com.codecool.movingmotivators.service.CardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.codecool.movingmotivators.model.CardSearch;

@RestController
@RequestMapping(path = "/cards")
@CrossOrigin(origins = {"${cross.origin.port.number}"})
public class CardController {

    @Autowired
    private CardService cardService;

    @GetMapping(value = "/get", produces = "application/json")
    public ResponseEntity getAllCards(@CookieValue("token") String token,
                                      @RequestParam("questionGroupName") String questionGroupName,
                                      @RequestParam("questions") String question) {
        return cardService.getCardsByQuestionGroupNameAndQuestionName(token, questionGroupName, question);
    }

    @PutMapping("/put")
    public ResponseEntity changeOrder(@CookieValue("token") String token,
                                      @RequestBody CardSearch cardSearch) {
        return cardService.changeCardsOrder(token, cardSearch.getQuestionGroupName(), cardSearch.getQuestionText(), cardSearch.getDragCardId(), cardSearch.getDropCardId());
    }
}
