package com.java.covid.controller;

import com.java.covid.model.Entry;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v1")
public class CovidRestController {

    @GetMapping("/")
    public List<Entry> get


}
