package com.codecool.movingmotivators.controller;

import com.codecool.movingmotivators.service.QuestionGroupService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@AllArgsConstructor
@CrossOrigin(origins = {"${cross.origin.port.number}"})
@RequestMapping(path = "/question-group")
public class QuestionGroupController {

    private final QuestionGroupService questionGroupService;

    @GetMapping
    public ResponseEntity questionGroups(@CookieValue("token") String token) {
        return questionGroupService.getQuestionGroupsByPersonId(token);
    }

}
