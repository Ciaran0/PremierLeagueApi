package com.springapp.mvc.dataStructures;

import lombok.Getter;

import java.util.ArrayList;
import java.util.Date;

@Getter
public class PremierLeagueTable {
    private ArrayList<TableEntry> tablelistings;
    private Location source;
    private long generatedDate;
    private String error = null;

    public PremierLeagueTable(Location source){
        tablelistings = new ArrayList<TableEntry>();
        this.source = source;
        this.generatedDate = new Date().getTime();
    }

    public void addTableEntry(TableEntry tableEntry){
        tablelistings.add(tableEntry);
    }

    public void clearTable(){
        tablelistings.clear();
    }

    public int size(){
        return tablelistings.size();
    }

    public void setError(String error){
        this.error= error;
    }

    @Override
    public boolean equals(Object object){
        return true;
    }

}
