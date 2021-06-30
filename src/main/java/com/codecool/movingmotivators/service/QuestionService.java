package com.codecool.movingmotivators.service;

import com.codecool.movingmotivators.model.Question;
import com.codecool.movingmotivators.repository.QuestionRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class QuestionService {

    private final QuestionRepository questionRepository;

    public void addQuestion(Question question) { questionRepository.save(question); }
}
