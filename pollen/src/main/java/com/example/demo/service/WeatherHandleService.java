package com.example.demo.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.example.demo.dto.RESULT;
import com.example.demo.dto.ResponceDTO;

@Service
public class WeatherHandleService {

	@Autowired
	@Qualifier("zipCodeSearchRestTemplate")
	RestTemplate restTemplate;

	/** 郵便番号検索API リクエストURL */
	private static final String URL = "http://zipcloud.ibsnet.co.jp/api/search?zipcode={zipcode}";

	private ResponceDTO service() {
//		OpenWeatherMapDTO dto = restTemplate.getForObject(URL, OpenWeatherMapDTO.class);
		return ResponceDTO.builder().weather("Clear").humidity(40).speed(7.21).build();
	}

	public String allocate(String cityName) {

		ResponceDTO dto = service();

		List<Boolean> resultList = new ArrayList<>();

		resultList.add(JudgeWeather.isMatch(dto.getWeather()));
		resultList.add(JudgeHumid.isMatch(dto.getHumidity()));
		resultList.add(JudgeWindSpeed.isMatch(dto.getSpeed()));

		return createResult(resultList).getDispWord();
	}

	private RESULT createResult(List<Boolean> results) {
		int count = (int) results.stream().filter(s -> s.booleanValue()).count();

		if (count == 0) {

			return RESULT.LOW;
		} else if (count == 3) {

			return RESULT.HIGH;
		} else {

			return RESULT.MID;
		}
	}
}
