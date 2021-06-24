package com.codecool.movingmotivators.repository;

import com.codecool.movingmotivators.model.QuestionModel;
import org.springframework.data.jpa.repository.JpaRepository;

public interface QuestionRepository extends JpaRepository<QuestionModel, Long> {
}
