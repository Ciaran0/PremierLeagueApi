package com.springapp.mvc.controllers;

import com.springapp.mvc.services.PremierLeagueTable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;

@Controller
@RequestMapping("/api/v1/getPremierLeagueTable")
public class PremierLeagueTableController {
    @Autowired
    private PremierLeagueTable premierLeagueTable;



	@RequestMapping(method = RequestMethod.GET)
	public @ResponseBody
    HashMap premierLeagueTable() {
		if(premierLeagueTable.generateTable()){
            return premierLeagueTable.getTable();
        }
        else {
            HashMap<String,String> error = new HashMap<String, String>();
            error.put("Error:","No data available");
            return error;
        }
	}
}