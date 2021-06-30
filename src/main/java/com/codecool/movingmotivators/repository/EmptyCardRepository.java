package com.codecool.movingmotivators.repository;

import com.codecool.movingmotivators.model.EmptyCard;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EmptyCardRepository extends JpaRepository<EmptyCard, Long> {
}
