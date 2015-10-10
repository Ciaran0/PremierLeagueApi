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
            Elements won = doc.getElementsByAttributeValue("template",".leagueTable-W");
            Elements drawn = doc.getElementsByAttributeValue("template",".leagueTable-D");
            Elements lost = doc.getElementsByAttributeValue("template",".leagueTable-L");
            Elements goalsFor = doc.getElementsByAttributeValue("template",".leagueTable-GF");
            Elements goalsAgainst = doc.getElementsByAttributeValue("template",".leagueTable-GA");
            Elements goalsDifference = doc.getElementsByAttributeValue("template",".leagueTable-GD");

            int count =0;
            for (Element name : teamNames ){
                int numPoints = Integer.parseInt(points.get(count).text());
                int numplayed = Integer.parseInt(played.get(count).text());
                int numWon = Integer.parseInt(won.get(count).text());
                int numDrawn = Integer.parseInt(drawn.get(count).text());
                int numLost = Integer.parseInt(lost.get(count).text());
                int numGoalsAgainst = Integer.parseInt(goalsAgainst.get(count).text());
                int numGoalsFor = Integer.parseInt(goalsFor.get(count).text());
                int numGoalDifference = Integer.parseInt(goalsDifference.get(count).text());
                TableEntry tableEntry = new TableEntry(name.text(),count+1,numplayed,numPoints,numWon,numDrawn,numLost,numGoalsAgainst,numGoalsFor,numGoalDifference);
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
