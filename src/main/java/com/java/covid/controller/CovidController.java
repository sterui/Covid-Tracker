package com.java.covid.controller;

import com.java.covid.service.CovidService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class CovidController {

    @Autowired
    CovidService covidService;

    @GetMapping("/")
    public String home(Model model) {
        model.addAttribute("entries", covidService.getAllEntries());
        model.addAttribute("totalCases", covidService.getTotal());
        model.addAttribute("totalNewCases", covidService.getTotalNew());
        return "home";
    }
}
