package com.codecool.movingmotivators.controller;

import com.codecool.movingmotivators.service.QuestionService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@AllArgsConstructor
@CrossOrigin(origins = {"${cross.origin.port.number}"})
@RequestMapping(path = "{questionGroupName}/questions")
public class QuestionController {

    private final QuestionService questionService;

    @GetMapping
    public ResponseEntity questions(@CookieValue("token") String token,
                                    @PathVariable("questionGroupName") String questionGroupName) {
        return questionService.getQuestionByQuestionGroupName(token, questionGroupName);
    }
}
