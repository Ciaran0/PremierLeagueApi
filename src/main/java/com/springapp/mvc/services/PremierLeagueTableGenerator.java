package com.springapp.mvc.services;


import com.datastax.driver.core.Session;
import com.google.gson.Gson;
import com.springapp.mvc.dataStructures.PremierLeagueTable;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cassandra.core.CqlOperations;
import org.springframework.data.cassandra.core.CassandraTemplate;
import org.springframework.scheduling.annotation.Scheduled;

import javax.annotation.PostConstruct;

import java.util.HashMap;

@Slf4j
public class PremierLeagueTableGenerator {

    @Getter
    private PremierLeagueTable table;
    @Autowired
    private Session session;
    @Autowired
    private BBCstats bbCstats;
    @Autowired
    private PremierLeagueStats premierLeagueStats;

    @PostConstruct
    public void init(){
        generateTable();
    }

    public boolean generateTable(){

        if(bbCstats.isResourceAvailable()){
            table = bbCstats.getPremierLeagueTable();
            return true;
        }
        else if(premierLeagueStats.isResourceAvailable()){
            table = premierLeagueStats.getPremierLeagueTable();
            return true;
        }
        else{
            return false;
        }
    }

    @Scheduled(cron="0 0/30 * * * ?")
    private void refreshTableAndSave(){
        bbCstats.getBBCdata();
        premierLeagueStats.getData();
    }

    @Scheduled(cron="59 59 23 * * ?")
    private void saveTable(){
        Gson gson = new Gson();
        String json = gson.toJson(table);
        log.info("Persisting table into database");
        CqlOperations cqlOperations = new CassandraTemplate(session);
        String query = "INSERT INTO premierleaguetable JSON '"+json+"'";
        cqlOperations.execute(query);
    }

    public HashMap<String,Boolean> areResourcesAvailable(){
        HashMap<String,Boolean> resources = new HashMap<String, Boolean>();
        resources.put("BBC",bbCstats.checkResourseAvailability());
        resources.put("PremierLeague.com",premierLeagueStats.checkResourseAvailability());
        return resources;
    }
}
