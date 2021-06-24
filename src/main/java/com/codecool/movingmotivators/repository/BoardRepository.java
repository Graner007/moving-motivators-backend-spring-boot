package com.codecool.movingmotivators.repository;

import com.codecool.movingmotivators.model.BoardModel;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BoardRepository extends JpaRepository<BoardModel, Long> {
}
