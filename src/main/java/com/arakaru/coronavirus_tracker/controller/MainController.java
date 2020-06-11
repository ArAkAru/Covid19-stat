package com.arakaru.coronavirus_tracker.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.arakaru.coronavirus_tracker.country.Country;
import com.arakaru.coronavirus_tracker.service.Data;

@Controller
public class MainController {
	@Autowired
	Data data;

	@GetMapping("/")
	public String home(Model model) {
		
		List<Country> countryList = data.getNewStats();
		int total = countryList.stream().mapToInt(country -> Integer.parseInt(country.getCount())).sum();
		model.addAttribute("stat", data.getNewStats());
		model.addAttribute("totalCount", total);
		return "home";
	}
}
