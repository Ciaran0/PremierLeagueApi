package com.springapp.mvc.services;


import com.springapp.mvc.dataStructures.TableEntry;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;

import javax.annotation.PostConstruct;
import java.util.HashMap;

public class PremierLeagueTable {

    private HashMap<Integer, TableEntry> table = new HashMap<Integer, TableEntry>();

    @Autowired
    private BBCstats bbCstats;
    @Autowired
    private EspnStats espnStats;
    @Autowired
    private PremierLeagueStats premierLeagueStats;

    @PostConstruct
    public void init(){
        generateTable();
    }

    public boolean generateTable(){

        if(bbCstats.getIsResourceAvailable()){
            table = bbCstats.getBbcTable();
            return true;
        }
        else if(premierLeagueStats.getIsResourceAvailable()){
            table = premierLeagueStats.getTable();
            return true;
        }
        else if(espnStats.getIsResourceAvailable()){
            table = espnStats.getTable();
            return true;
        }
        else{
            return false;
        }
    }
    @Scheduled(cron="0 0/30 * * * ?")
    private void refreshTable(){
        bbCstats.getBBCdata();
        espnStats.getData();
        premierLeagueStats.getData();
    }

    public HashMap<Integer, TableEntry> getTable(){
        return table;
    }

    public HashMap<String,Boolean> areResourcesAvailable(){
        HashMap<String,Boolean> resources = new HashMap<String, Boolean>();
        resources.put("BBC",bbCstats.isResourceAvailable());
        resources.put("ESPN",espnStats.isResourceAvailable());
        resources.put("PremierLeague.com",premierLeagueStats.isResourceAvailable());
        return resources;
    }


}
