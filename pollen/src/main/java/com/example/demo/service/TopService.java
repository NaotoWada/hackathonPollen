package com.example.demo.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.example.demo.dto.OpenWeatherMapDTO;
import com.example.demo.dto.REGION;
import com.example.demo.dto.ResponceDTO;

import lombok.Getter;

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
	private static final String URL_BEFORE = "https://api.openweathermap.org/data/2.5/forecast?q=";
	private static final String URL_AFTER = ",jp&APPID=12d670fe1c3082cf7a71a0492679eb2c";

	/** OpenWeatherAPI RequestURL */
	private static final String URL_MULTI_BEFORE = "https://api.openweathermap.org/data/2.5/group?id=";
	private static final String URL_MULTI_AFTER = "&units=metric&APPID=12d670fe1c3082cf7a71a0492679eb2c";

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

	/**
	 * <p>
	 * Request json data of whole Japan prefecture from OpenWeatherAPI.
	 * 
	 * @return json Object Map
	 */
	public Map<String, OpenWeatherMapDTO> requestOpenWeatherAPIthenStoreMap() {
		List<ResponceDTO> responceDtoList = requestDtoBy(REGION.getIdList());
		Map<String, OpenWeatherMapDTO> idDtoMap = convertMap(responceDtoList);
		return REGION.store(idDtoMap);
	}

	private Map<String, OpenWeatherMapDTO> convertMap(List<ResponceDTO> responceDtoList) {
		Map<String, OpenWeatherMapDTO> retMap = new HashMap<>();
		for (ResponceDTO responceDTO : responceDtoList) {
			for (OpenWeatherMapDTO weatherDto : responceDTO.getList()) {
				retMap.put(weatherDto.getName(), weatherDto);
			}
		}
		return retMap;
	}

	private List<ResponceDTO> requestDtoBy(List<String> idList) {

		List<ResponceDTO> retList = new ArrayList<>();
		for (REQUEST_DIVIDE requestTime : REQUEST_DIVIDE.values()) {
			String url = URL_MULTI_BEFORE + extratId(idList, requestTime) + URL_MULTI_AFTER;
			
			System.out.println(url);
			
			ResponceDTO e = restTemplate.getForObject(url, ResponceDTO.class);
			retList.add(e);
		}
		return retList;
	}

	private String extratId(List<String> idList, REQUEST_DIVIDE div) {
		int start = div.getStart();
		int end = div.getEnd();

		StringBuilder sb = new StringBuilder();
		for (; start < end; start++) {
			if (start == end - 1) {

				// last index as this loop
				sb.append(idList.get(start));
			} else {

				sb.append(idList.get(start));
				sb.append(",");
			}
		}
		return sb.toString();
	}

	/**
	 * <p>
	 * Quantity ID per API request.
	 * 
	 * @author Naoto Wada
	 *
	 */
	enum REQUEST_DIVIDE {
		FIRST(0, 20), //
		SECOND(20, 40), //
		THIRD(40, 47),;//

		@Getter
		private final int start;
		@Getter
		private final int end;

		private REQUEST_DIVIDE(int start, int end) {
			this.start = start;
			this.end = end;
		}
	}
}
