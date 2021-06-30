package com.codecool.movingmotivators.service;

import com.codecool.movingmotivators.model.QuestionGroup;
import com.codecool.movingmotivators.repository.QuestionGroupRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class QuestionGroupService {

    private final QuestionGroupRepository questionGroupRepository;

    public void addQuestionGroup(QuestionGroup questionGroup) { questionGroupRepository.save(questionGroup); }
}
