package com.springapp.mvc.controllers;

import com.springapp.mvc.services.BBCstats;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import java.util.HashMap;

@Controller
@RequestMapping("/api/premierLeagueTable")
public class PremierLeagueTableController {
    @Autowired BBCstats bbcStats;

	@RequestMapping(method = RequestMethod.GET)
	public @ResponseBody
    HashMap printWelcome() {
        bbcStats.getBBCdata();
		return bbcStats.getBbcTable();
	}
}