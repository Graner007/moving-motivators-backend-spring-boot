package com.codecool.movingmotivators.service;

import com.codecool.movingmotivators.model.QuestionGroup;
import com.codecool.movingmotivators.repository.QuestionGroupRepository;
import com.codecool.movingmotivators.security.jwt.JwtService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class QuestionGroupService {

    private final QuestionGroupRepository questionGroupRepository;
    private final JwtService jwtService;

    public void addQuestionGroup(QuestionGroup questionGroup) { questionGroupRepository.save(questionGroup); }

    public ResponseEntity getQuestionGroupsByPersonId(String bearerToken) {
        if (bearerToken != null) {
            String token = jwtService.getTokenWithoutBearer(bearerToken);
            long id = jwtService.parseIdFromTokenInfo(token);

            return ResponseEntity.ok(questionGroupRepository.findAllByPersonId(id));
        }

        return ResponseEntity.badRequest().body("Token can not be null!");
    }
}
