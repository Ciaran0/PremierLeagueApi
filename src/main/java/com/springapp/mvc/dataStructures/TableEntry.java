package com.springapp.mvc.dataStructures;

public class TableEntry {
    private String teamname;
    private int position,played,points;

    public TableEntry(int position,String teamname, int played, int points){
        this.position = position;
        this.teamname =  teamname;
        this.points = points;
        this.played = played;
    }
    public String getTeamname(){
        return teamname;
    }
    public int getPlayed(){
        return played;
    }
    public int getPoints(){
        return points;
    }
    public int getPosition(){
        return position;
    }


}
