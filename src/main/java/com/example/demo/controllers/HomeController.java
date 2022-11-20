package com.example.demo.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.example.demo.model.LocationStats;
import com.example.demo.service.DataService;

@Controller
public class HomeController {

	@Autowired
	DataService dataService;
	
	@GetMapping("/")
	public String home(Model model)
	{
		List <LocationStats> allStats = dataService.getAllStats();
		int totalReportedCases=allStats.stream().mapToInt(stat -> Integer.parseInt(stat.getLatestTotalCases())).sum();
		model.addAttribute("locationStats",allStats);
		model.addAttribute("totalReportedCases", totalReportedCases);
		
		return "home";
	}
}
