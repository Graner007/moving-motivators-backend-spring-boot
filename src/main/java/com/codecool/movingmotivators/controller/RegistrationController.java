package com.codecool.movingmotivators.controller;

import com.codecool.movingmotivators.model.Person;
import com.codecool.movingmotivators.service.PersonService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequestMapping(path = "/registration")
@RestController
@CrossOrigin(origins = {"${cross.origin.port.number}"})
@AllArgsConstructor
public class RegistrationController {

    private final PersonService personService;

    @GetMapping
    public ResponseEntity register(@RequestBody Person person) { return personService.register(person); }
}
