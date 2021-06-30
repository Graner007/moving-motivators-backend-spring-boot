package com.codecool.movingmotivators.repository;

import com.codecool.movingmotivators.model.RoleKey;
import com.codecool.movingmotivators.model.UserRole;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RoleRepository extends JpaRepository<UserRole, RoleKey> {

    List<UserRole> findAllByPersonId(long personId);

}
