package com.example.demo.dto;

import java.util.List;
import java.util.Map;

import lombok.Data;

@Data
public class ResponceDTO {
	String cod;
	double message;
	int cnt;
	Map<String, Object> city;
	List<OpenWeatherMapDTO> list;

	public String getWeather() {
		String weather = this.list.get(0).getWeather().get(0).getMain();
		System.out.println(weather);
		return weather;
	}
	public int getHumidity() {
		int humidity = this.list.get(0).getMain().getHumidity();
		System.out.println(humidity);
		return humidity;
	}
	public double getSpeed() {
		double speed = this.list.get(0).getWind().getSpeed();
		System.out.println(speed);
		return speed;
	}
	
}
