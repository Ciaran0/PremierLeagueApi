package com.springapp.mvc.dataStructures;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;

import java.io.Serializable;

@Getter
@AllArgsConstructor
@ToString
public class TableEntry implements Serializable {
    private String teamname;
    private int position,played,won,drawn,lost,goalsFor,goalsAgainst,goalDifference,points;
}
