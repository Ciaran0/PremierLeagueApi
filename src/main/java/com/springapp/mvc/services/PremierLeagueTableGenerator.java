package com.springapp.mvc.services;


import com.springapp.mvc.dataStructures.PremierLeagueTable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;

import javax.annotation.PostConstruct;
import java.util.HashMap;

public class PremierLeagueTableGenerator {

    private PremierLeagueTable table;

    @Autowired
    private BBCstats bbCstats;
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

        else{
            return false;
        }
    }
    @Scheduled(cron="0 0/30 * * * ?")
    private void refreshTable(){
        bbCstats.getBBCdata();
        premierLeagueStats.getData();
    }

    public PremierLeagueTable getTable(){
        return table;
    }

    public HashMap<String,Boolean> areResourcesAvailable(){
        HashMap<String,Boolean> resources = new HashMap<String, Boolean>();
        resources.put("BBC",bbCstats.isResourceAvailable());
        resources.put("PremierLeague.com",premierLeagueStats.isResourceAvailable());
        return resources;
    }


}
