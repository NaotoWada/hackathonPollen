package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.demo.service.RegionDataService;

@Controller
public class JapanController {

	@Autowired
	RegionDataService service;

	@RequestMapping("/japan/")
	public String test(Model model, @RequestParam(name = "cityName", required = false) String cityName) {

		System.out.println(cityName);

		model.addAttribute("cityName", cityName);
		model.addAttribute("weather", service.getWeather(cityName));
		model.addAttribute("humid", service.getHumid(cityName));
		model.addAttribute("windSpeed", service.getWindSpeed(cityName));
		model.addAttribute("viewDate", service.currentTime());

		return "japan";
	}
}
