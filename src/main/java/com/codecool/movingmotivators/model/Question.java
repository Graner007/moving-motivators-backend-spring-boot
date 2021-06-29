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
@Table(name = "question")
public class Question {

    @GeneratedValue
    @Id
    private Long id;

    @ManyToOne
    @JoinColumn(referencedColumnName = "id")
    private QuestionGroup questionGroup;

    private String questionText;

    private Boolean finalized;
}
