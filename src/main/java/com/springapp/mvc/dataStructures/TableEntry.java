package com.springapp.mvc.dataStructures;

public class TableEntry {
    private String teamname;
    private int points;

    public TableEntry(String teamname, int points){
        this.teamname =  teamname;
        this.points = points;
    }
    public String getTeamname(){
        return teamname;
    }
    public int getPoints(){
        return points;
    }

}
