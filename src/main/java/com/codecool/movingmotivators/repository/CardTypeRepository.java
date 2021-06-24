package com.codecool.movingmotivators.repository;

import com.codecool.movingmotivators.model.CardTypeModel;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CardTypeRepository extends JpaRepository<CardTypeModel, Long> {
}
