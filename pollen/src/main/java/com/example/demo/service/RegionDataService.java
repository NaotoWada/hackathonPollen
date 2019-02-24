package com.example.demo.service;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.temporal.ChronoUnit;

import org.springframework.stereotype.Service;

@Service
public class RegionDataService {

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

	public Object getWeather(String cityName) {
		return null;
	}

	public Object getHumid(String cityName) {
		return null;
	}

	public Object getWindSpeed(String cityName) {
		return null;
	}
}
