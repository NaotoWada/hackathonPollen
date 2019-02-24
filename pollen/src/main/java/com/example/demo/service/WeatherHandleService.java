package com.example.demo.service;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.dto.REGION;
import com.example.demo.dto.RESULT;
import com.example.demo.dto.ResponceDTO;

/**
 * <p>
 * Handle weather data from API.
 * 
 * @author Naoto Wada
 */
@Service
public class WeatherHandleService {

	@Autowired
	private TopService service;

	/**
	 * <p>
	 * Create pollen affection result from RequestAPI data which is involved input
	 * city name.
	 * 
	 * @param cityName if {@code null} then {@code throw NullpointerException}.<br>
	 *                 only adopt Japanese region.
	 * @return view word as result
	 */
	public String getResult(String cityName) {

		Objects.requireNonNull(cityName);

		ResponceDTO dto = service.requestOpenWeatherAPI(cityName);

		List<Boolean> resultList = new ArrayList<>();

		resultList.add(JudgeWeather.isMatch(dto.getWeather()));
		resultList.add(JudgeHumid.isMatch(dto.getHumidity()));
		resultList.add(JudgeWindSpeed.isMatch(dto.getSpeed()));

		return PollenResultCreator.create(resultList);
	}

	/**
	 * <p>
	 * Get currentTime as specific format. truncate milliseconds.
	 * 
	 * @return time truncated Millseconds
	 */
	public String currentTime() {
		LocalDateTime now = LocalDateTime.now(ZoneId.systemDefault()).truncatedTo(ChronoUnit.SECONDS);

		StringBuilder sb = new StringBuilder();
		sb.append("[");
		sb.append(String.valueOf(now.getYear()));
		sb.append("年");
		sb.append(String.valueOf(now.getMonth().getValue()));
		sb.append("月");
		sb.append(String.valueOf(now.getDayOfMonth()));
		sb.append("日 ");
		sb.append(String.valueOf(now.getHour()));
		sb.append("時");
		sb.append(String.valueOf(now.getMinute()));
		sb.append("分");
		sb.append(String.valueOf(now.getSecond()));
		sb.append("秒");
		sb.append("]");

		return sb.toString();
	}

	/**
	 * <p>
	 * Get city by English name region.
	 * 
	 * @param cityName if {@code null} then {@code throw NullpointerException}.<br>
	 *                 only adopt Japanese region.
	 * @return Japanese city name
	 */
	public String getJapaneseCityName(String cityName) {
		return REGION.toJpName(cityName);
	}
}
