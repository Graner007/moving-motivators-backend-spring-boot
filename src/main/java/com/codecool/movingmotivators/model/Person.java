package com.codecool.movingmotivators.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Builder
@Table(name = "person")
public class Person {

    @GeneratedValue
    @Id
    private Long id;

    private String username;
    private String password;
    private String email;
    private LocalDate registrationDate;
}
