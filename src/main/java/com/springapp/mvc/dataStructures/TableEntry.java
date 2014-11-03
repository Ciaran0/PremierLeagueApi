package com.springapp.mvc.dataStructures;

/**
 * Created by ciaran on 02/11/14.
 */
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
