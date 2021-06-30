package com.codecool.movingmotivators.repository;

import com.codecool.movingmotivators.model.Card;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Collection;

@Repository
public interface CardRepository extends JpaRepository<Card, Long> {

    Collection<Card> getCardsByQuestionId(long id);

}
