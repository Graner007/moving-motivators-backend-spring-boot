package com.codecool.movingmotivators.repository;

import com.codecool.movingmotivators.model.PersonModel;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PersonRepository extends JpaRepository<PersonModel, Long> {
}
