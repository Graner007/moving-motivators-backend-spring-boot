package com.codecool.movingmotivators.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Builder
@Table(name = "board")
public class Board {

    @GeneratedValue
    @Id
    private Long id;

    @OneToOne
    private Person person;

    @OneToOne
    private Question question;
}
