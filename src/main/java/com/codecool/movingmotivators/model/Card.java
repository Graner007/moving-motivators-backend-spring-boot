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
@Table(name = "card")
public class Card {

    @GeneratedValue
    @Id
    private Long id;

    private int orderNumber;

    /*@Enumerated(EnumType.STRING)
    private VerticalStatusName verticalStatusName;*/
    private String verticalStatusName;

    @ManyToOne
    @JoinColumn(referencedColumnName = "id")
    private Board board;

    @OneToOne
    private CardType cardType;
}
