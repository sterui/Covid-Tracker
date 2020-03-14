package com.java.covid.controller;

import com.java.covid.model.Entry;
import com.java.covid.service.CovidService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/v1")
public class CovidRestController {

    @Autowired
    CovidService covidService;

    @GetMapping("/totalcases")
    public int getTotalCases() {

        return covidService.getTotal();
    }

    @GetMapping("/casescountry/{country}")
    public int latestCasesPerCountry(@PathVariable(value="country") String country) {

        List<Entry> entries =  covidService.getAllEntries();
        List<Entry> selected = entries.stream().filter(entry -> country.equals(entry.getCountry())).collect(Collectors.toList());
        return selected.stream().mapToInt(entry -> entry.getDeltaPreviousDate()).sum();
    }


}
