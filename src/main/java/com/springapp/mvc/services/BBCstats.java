package com.springapp.mvc.services;

import com.springapp.mvc.dataStructures.*;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;

/**
 * Stats taken from BBC.com
 */
public class BBCstats {

    private PremierLeagueTable premierLeagueTable = new PremierLeagueTable(Location.BBC) ;
    private final String urlOfTable = "http://www.bbc.com/sport/football/tables";
    private boolean isResourceAvailable;

    public BBCstats(){
        isResourceAvailable();
    }

    public boolean getBBCdata() {
        try {
            premierLeagueTable.clearTable();
            Document doc = Jsoup.connect(urlOfTable).get();
            Elements teamNames = doc.getElementsByAttribute("data-team-slug");
            Elements points = doc.getElementsByAttributeValue("class","points").not("th");
            Elements played = doc.getElementsByAttributeValue("class","played").not("th");
            int count =0;
            for (Element name : teamNames ){
                String club = name.attr("data-team-slug");
                int numPoints = Integer.parseInt(points.get(count).text());
                int numPlayed = Integer.parseInt(played.get(count).text());
                TableEntry tableEntry = new TableEntry(count+1,club,numPlayed,numPoints);
                premierLeagueTable.addTableEntry(tableEntry);
                count++;
            }
        } catch (IOException e) {
            return false;
        }
        return true;
    }
    public PremierLeagueTable getBbcTable(){
        return premierLeagueTable;
    }

    public boolean isResourceAvailable(){
        isResourceAvailable = getBBCdata();
        return isResourceAvailable;
    }

    public boolean getIsResourceAvailable(){
        return isResourceAvailable;
    }
}
