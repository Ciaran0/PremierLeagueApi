package com.springapp.mvc.services;

import com.springapp.mvc.dataStructures.Location;
import com.springapp.mvc.dataStructures.PremierLeagueTable;
import com.springapp.mvc.dataStructures.TableEntry;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;

/**
 * Stats taken from PremierLeague.com
 */
@Slf4j
public class PremierLeagueStats {
    @Getter
    private PremierLeagueTable premierLeagueTable = new PremierLeagueTable(Location.PremierLeague);
    @Getter
    private boolean isResourceAvailable;

    public PremierLeagueStats(){
        checkResourseAvailability();
    }

    public boolean getData() {
        final String urlOfTable = "http://www.premierleague.com/en-gb/matchday/league-table.html";
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
            log.error("Error getting premier league table from premierleague.com");
            log.error(e.getMessage());
            return false;
        }
        return true;
    }
    public boolean checkResourseAvailability(){
        isResourceAvailable = getData();
        return isResourceAvailable;
    }
}
