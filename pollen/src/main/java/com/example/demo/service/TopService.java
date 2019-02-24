package com.example.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.example.demo.dto.OpenWeatherMapDTO;
import com.example.demo.dto.ResponceDTO;

@Service
public class TopService {

	@Autowired
	@Qualifier("zipCodeSearchRestTemplate")
	RestTemplate restTemplate;

	/** 郵便番号検索API リクエストURL */
	private static final String URL = "http://zipcloud.ibsnet.co.jp/api/search?zipcode={zipcode}";

	public ResponceDTO service() {
		OpenWeatherMapDTO dto = restTemplate.getForObject(URL, OpenWeatherMapDTO.class);
		return ResponceDTO.builder().weather("Clear").humidity(40).speed(7.21).build();
	}
}
