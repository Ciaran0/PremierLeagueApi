package com.springapp.mvc.services;

import com.springapp.mvc.dataStructures.Location;
import com.springapp.mvc.dataStructures.PremierLeagueTable;
import com.springapp.mvc.dataStructures.TableEntry;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

/**
 * Stats taken from PremierLeague.com
 */
public class PremierLeagueStats {
    private PremierLeagueTable premierLeagueTable = new PremierLeagueTable(Location.PremierLeague);
    private final String urlOfTable = "http://www.premierleague.com/en-gb/matchday/league-table.html";
    private boolean isResourceAvailable;

    public PremierLeagueStats(){
        isResourceAvailable();
    }

    public boolean getData() {
        try {
            Document doc = Jsoup.connect(urlOfTable).get();
            Elements teamNames = doc.getElementsByAttributeValue("template", ".leagueTable-Club");
            Elements points = doc.getElementsByAttributeValue("template", ".leagueTable-Pts");
            Elements played = doc.getElementsByAttributeValue("template", ".leagueTable-P");
            int count =0;
            for (Element name : teamNames ){
                int numPoints = Integer.parseInt(points.get(count).text());
                int numplayed = Integer.parseInt(played.get(count).text());
                TableEntry tableEntry = new TableEntry(count+1,name.text(),numplayed,numPoints);
                premierLeagueTable.addTableEntry(tableEntry);
                count++;
            }
        } catch (IOException e) {
            return false;
        }
        return true;
    }
    public PremierLeagueTable getTable(){
        return premierLeagueTable;
    }
    public boolean isResourceAvailable(){
        isResourceAvailable = getData();
        return isResourceAvailable;
    }

    public boolean getIsResourceAvailable(){
        return isResourceAvailable;
    }
}
