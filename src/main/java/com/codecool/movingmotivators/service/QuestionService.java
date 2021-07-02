package com.codecool.movingmotivators.service;

import com.codecool.movingmotivators.model.Question;
import com.codecool.movingmotivators.model.QuestionGroup;
import com.codecool.movingmotivators.repository.QuestionRepository;
import com.codecool.movingmotivators.security.jwt.JwtService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Collection;

@Service
@AllArgsConstructor
public class QuestionService {

    private final QuestionRepository questionRepository;
    private final QuestionGroupService questionGroupService;
    private final JwtService jwtService;

    public void addQuestion(Question question) { questionRepository.save(question); }

    public ResponseEntity getQuestionByQuestionGroupName(String bearerToken, String questionGroupName) {
        if (bearerToken != null) {
            String token = jwtService.getTokenWithoutBearer(bearerToken);
            long id = jwtService.parseIdFromTokenInfo(token);

            QuestionGroup questionGroup = questionGroupService.getQuestionGroupByGroupName(questionGroupName, id);

            return ResponseEntity.ok(questionRepository.getQuestionsByQuestionGroupId(questionGroup.getId()));
        }

        return ResponseEntity.badRequest().body("Token can not be null!");
    }

    public Question getQuestionByName(String name) {return questionRepository.getQuestionByQuestionText(name); }

}
