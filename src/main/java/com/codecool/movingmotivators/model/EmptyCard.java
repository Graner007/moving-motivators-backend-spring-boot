package com.codecool.movingmotivators.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "empty_card")
public class EmptyCard {

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private long id;

    @OneToOne
    private Card card;

    @Enumerated(EnumType.STRING)
    private VerticalStatusName verticalStatusName;

    @OneToOne
    private CardType cardType;
}
