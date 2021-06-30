package com.codecool.movingmotivators.controller;

import com.codecool.movingmotivators.security.jwt.JwtService;
import com.codecool.movingmotivators.service.CardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping(path = "/cards")
@CrossOrigin(origins = {"${cross.origin.port.number}"})
public class CardController {

    @Autowired
    private CardService cardService;

    @Autowired
    private JwtService jwtService;

    @GetMapping()
    public ResponseEntity getAllCards(HttpServletRequest req) {
        String token = jwtService.getTokenFromRequest(req);
        if (token == null)
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();

        return ResponseEntity.ok(cardService.getAllCards());
    }
}
