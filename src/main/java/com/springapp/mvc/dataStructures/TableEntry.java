package com.springapp.mvc.dataStructures;

import lombok.Getter;

import java.io.Serializable;

@Getter
public class TableEntry implements Serializable {
    private String teamname;
    private int position,played,points;

    public TableEntry(int position,String teamname, int played, int points){
        this.position = position;
        this.teamname =  teamname;
        this.points = points;
        this.played = played;
    }

    @Override
    public String toString(){
        return "{position: "+position+", teamname: "+teamname+", points: "+points+", played: "+played+"}";
    }

}
