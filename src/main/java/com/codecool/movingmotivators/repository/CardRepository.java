package com.codecool.movingmotivators.repository;

import com.codecool.movingmotivators.model.CardModel;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CardRepository extends JpaRepository<CardModel, Long> {
}
