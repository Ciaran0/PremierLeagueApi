package com.springapp.mvc.services;

import com.springapp.mvc.dataStructures.*;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;

/**
 * Stats taken from BBC.com
 */
@Slf4j
public class BBCstats {

    @Getter
    private PremierLeagueTable premierLeagueTable = new PremierLeagueTable(Location.BBC) ;
    @Getter
    private boolean isResourceAvailable;

    public BBCstats(){
        checkResourseAvailability();
    }

    public boolean getBBCdata() {
        final String urlOfTable = "http://www.bbc.com/sport/football/tables";
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
            log.error("Error getting BBC premierleague table");
            log.error(e.getMessage());
            return false;
        }
        return true;
    }

    public boolean checkResourseAvailability(){
        isResourceAvailable = getBBCdata();
        return isResourceAvailable;
    }


}
