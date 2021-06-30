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
@Table(name = "empty_card")
public class EmptyCard {

    @GeneratedValue
    @Id
    private long id;

    @Enumerated(EnumType.STRING)
    private VerticalStatusName verticalStatusName;

    @OneToOne
    private CardType cardType;
}
