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
public class CardModel {

    @GeneratedValue
    @Id
    private Long id;

    private int orderNumber;
    private VerticalStatusType verticalStatusType;

    @ManyToOne
    private BoardModel boardModel;

    @OneToOne
    private CardTypeModel cardTypeModel;
}
