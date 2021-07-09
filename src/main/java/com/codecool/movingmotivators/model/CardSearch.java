package com.codecool.movingmotivators.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CardSearch {

    private String questionGroupName;

    private String questionText;

    private String dragCardId;

    private String dropCardId;
}
