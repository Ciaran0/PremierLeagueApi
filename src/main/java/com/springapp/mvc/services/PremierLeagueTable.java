package com.springapp.mvc.services;

import com.springapp.mvc.dataStructures.TableEntry;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import java.io.IOException;
import java.util.HashMap;

/**
 * Stats taken from PremierLeague.com
 */
public class PremierLeagueTable {
    private HashMap<Integer, TableEntry> table = new HashMap<Integer, TableEntry>();
    private final String urlOfTable = "http://www.premierleague.com/en-gb/matchday/league-table.html";

    public PremierLeagueTable(){
        getData();
    }

    private boolean getData() {
        try {
            Document doc = Jsoup.connect(urlOfTable).get();
            Elements teamNames = doc.getElementsByAttributeValue("template", ".leagueTable-Club");
            Elements points = doc.getElementsByAttributeValue("template", ".leagueTable-Pts");
            int count =0;
            for (Element name : teamNames ){
                int numPoints = Integer.parseInt(points.get(count).text());
                TableEntry tableEntry = new TableEntry(name.text(),numPoints);
                table.put(count+1, tableEntry);
                count++;
            }
        } catch (IOException e) {
            return false;
        }
        return true;
    }
    public HashMap<Integer, TableEntry> getTable(){
        return table;
    }
}
