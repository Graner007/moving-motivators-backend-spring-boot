package com.codecool.movingmotivators.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Builder
public class PersonModel {

    @GeneratedValue
    @Id
    private Long id;

    private String username;
    private String password;
    private String email;
    private LocalDate registrationDate;

}
