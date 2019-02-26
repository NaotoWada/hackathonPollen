package com.example.demo.dto;

import java.util.List;

import lombok.Data;

@Data
public class OpenWeatherMapDTO {
	String name;
	int id;
	Humid main;
	List<Weather> weather; 
	Wind wind;
}
