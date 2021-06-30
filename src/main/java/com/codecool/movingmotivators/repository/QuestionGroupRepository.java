package com.codecool.movingmotivators.repository;

import com.codecool.movingmotivators.model.QuestionGroup;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Collection;

@Repository
public interface QuestionGroupRepository extends JpaRepository<QuestionGroup, Long> {

    Collection<QuestionGroup> findAllByPersonId(long id);

    QuestionGroup getQuestionGroupByGroupNameAndPersonId(String groupName, long personId);

}
