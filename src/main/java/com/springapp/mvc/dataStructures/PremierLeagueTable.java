package com.springapp.mvc.dataStructures;

import java.util.ArrayList;
import java.util.Date;

public class PremierLeagueTable {
    private ArrayList<TableEntry> premierLeagueTable;
    private Location source;
    private Date dateGenerated;
    private String error = null;

    public PremierLeagueTable(Location source){
        premierLeagueTable = new ArrayList<TableEntry>();
        this.source = source;
        this.dateGenerated = new Date();
    }

    public void addTableEntry(TableEntry tableEntry){
        premierLeagueTable.add(tableEntry);
    }

    public void clearTable(){
        premierLeagueTable.clear();
    }

    public int size(){
        return premierLeagueTable.size();
    }

    public void setError(String error){
        this.error= error;
    }

    public String getError(){
        return error;
    }

    public String getLocation(){
        return source.name();
    }

    public Date getDateGenerated(){
        return dateGenerated;
    }

    public ArrayList<TableEntry> getTable(){
        return premierLeagueTable;
    }
}
