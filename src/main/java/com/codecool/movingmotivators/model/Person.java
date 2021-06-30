package com.codecool.movingmotivators.model;

import lombok.*;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;

@Getter
@Setter
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
    private LocalDate registrationDate;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "person", cascade = CascadeType.ALL)
    private List<UserRole> roles;
}
