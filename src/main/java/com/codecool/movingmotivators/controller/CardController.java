package com.codecool.movingmotivators.controller;

import com.codecool.movingmotivators.service.CardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "/{questionGroupName}/{questions}/cards")
@CrossOrigin(origins = {"${cross.origin.port.number}"})
public class CardController {

    @Autowired
    private CardService cardService;

    @GetMapping()
    public ResponseEntity getAllCards(@CookieValue("token") String token,
                                      @PathVariable("questionGroupName") String questionGroupName,
                                      @PathVariable("questions") String question) {
        return cardService.getCardsByQuestionGroupNameAndQuestionName(token, questionGroupName, question);
    }

    @PutMapping()
    public ResponseEntity changeOrder(@CookieValue("token") String token,
                                      @PathVariable("questionGroupName") String questionGroupName,
                                      @PathVariable("questions") String question,
                                      @RequestParam("dragCardId") String dragCardId,
                                      @RequestParam("dropCardId") String dropCardId) {
        return cardService.changeCardsOrder(token, questionGroupName, question, dragCardId, dropCardId);
    }
}
