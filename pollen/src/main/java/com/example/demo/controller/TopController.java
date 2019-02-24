package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.demo.service.WeatherHandleService;

@Controller
public class TopController {

	@Autowired
	private WeatherHandleService service;

	@RequestMapping("/")
	public String index(Model model, @RequestParam(name = "cityName", required = false) String cityName) {

		model.addAttribute("cityName", service.getJapaneseCityName(cityName));
		model.addAttribute("pollen", service.getResult(cityName));
		model.addAttribute("viewDate", service.currentTime());

		return "index";
	}
}
