package com.codecool.movingmotivators.repository;

import com.codecool.movingmotivators.model.Question;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Collection;

@Repository
public interface QuestionRepository extends JpaRepository<Question, Long> {

    Collection<Question> getQuestionsByQuestionGroupId(long id);

}
