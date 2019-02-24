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
	public String index(Model model, @RequestParam String cityName) {

		model.addAttribute("cityName", cityName);
		model.addAttribute("pollen", service.allocate(cityName));

		return "index";
	}
}
