package com.codecool.movingmotivators.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Builder
@Table(name = "card")
public class Card {

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private Long id;

    private int orderNumber;

    @Enumerated(EnumType.STRING)
    private VerticalStatusName verticalStatusName;

    @ManyToOne
    @JoinColumn(referencedColumnName = "id")
    private Question question;

    @OneToOne
    private CardType cardType;
}
