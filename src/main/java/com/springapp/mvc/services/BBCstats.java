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
            Elements won = doc.getElementsByAttributeValue("class","won").not("th");
            Elements drawn = doc.getElementsByAttributeValue("class","drawn").not("th");
            Elements lost = doc.getElementsByAttributeValue("class","lost").not("th");
            Elements goalsFor = doc.getElementsByAttributeValue("class","for").not("th");
            Elements goalsAgainst = doc.getElementsByAttributeValue("class","against").not("th");
            Elements goalsDifference = doc.getElementsByAttributeValue("class","goal-difference").not("th");

            int count =0;
            for (Element name : teamNames ){
                String club = name.attr("data-team-slug");
                int numPoints = Integer.parseInt(points.get(count).text());
                int numPlayed = Integer.parseInt(played.get(count).text());
                int numWon = Integer.parseInt(won.get(count).text());
                int numDrawn = Integer.parseInt(drawn.get(count).text());
                int numLost = Integer.parseInt(lost.get(count).text());
                int numGoalsAgainst = Integer.parseInt(goalsAgainst.get(count).text());
                int numGoalsFor = Integer.parseInt(goalsFor.get(count).text());
                int numGoalDifference = Integer.parseInt(goalsDifference.get(count).text());
                TableEntry tableEntry = new TableEntry(club,count+1,numPlayed,numWon,numDrawn,numLost,numGoalsAgainst,numGoalsFor,numGoalDifference,numPoints);
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
