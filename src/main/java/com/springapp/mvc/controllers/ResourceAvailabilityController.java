package com.springapp.mvc.controllers;

import com.springapp.mvc.services.PremierLeagueTableGenerator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;

@Controller
@RequestMapping("/api/resourceAvailability")
public class ResourceAvailabilityController {
    @Autowired
    private PremierLeagueTableGenerator premierLeagueTable;

    @RequestMapping(method = RequestMethod.GET)
    public @ResponseBody
    HashMap printAvailability() {
        return premierLeagueTable.areResourcesAvailable();
    }
}
