package com.codecool.movingmotivators.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToOne;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Builder
public class BoardModel {

    @GeneratedValue
    @Id
    private Long id;

    @OneToOne
    private PersonModel personModel;

    @OneToOne
    private QuestionModel questionModel;
}
