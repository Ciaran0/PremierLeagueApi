package com.springapp.mvc.controllers;

import com.springapp.mvc.dataStructures.PremierLeagueTable;
import com.springapp.mvc.services.PremierLeagueTableGenerator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;

@Controller
@RequestMapping("/api/v1/getPremierLeagueTable")
public class PremierLeagueTableController {
    @Autowired
    private PremierLeagueTableGenerator premierLeagueTableGenerator;

	@RequestMapping(method = RequestMethod.GET)
	public @ResponseBody
    PremierLeagueTable premierLeagueTable() {
		if(!premierLeagueTableGenerator.generateTable()){
            premierLeagueTableGenerator.getTable().setError("Error: No data available");
        }
        return premierLeagueTableGenerator.getTable();
	}
}