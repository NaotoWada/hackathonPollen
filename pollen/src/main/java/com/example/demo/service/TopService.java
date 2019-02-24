package com.example.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.example.demo.dto.ResponceDTO;

/**
 * <p>
 * OpenWeatherAPI accessor class.
 * 
 * @author Naoto Wada
 */
@Service
public class TopService {

	@Autowired
	@Qualifier("zipCodeSearchRestTemplate")
	RestTemplate restTemplate;

	/** OpenWeatherAPI RequestURL */
	private static final String URL_BEFORE = "http://api.openweathermap.org/data/2.5/forecast?q=";
	private static final String URL_AFTER = ",jp&APPID=12d670fe1c3082cf7a71a0492679eb2c";

	/**
	 * <p>
	 * Request json data from OpenWeatherAPI.
	 * 
	 * @param cityName EnglishName of Japanese region
	 * @return json object
	 */
	public ResponceDTO requestOpenWeatherAPI(String cityName) {
		String url = URL_BEFORE + cityName + URL_AFTER;
		ResponceDTO dto = restTemplate.getForObject(url, ResponceDTO.class);
		return dto;
	}
}
