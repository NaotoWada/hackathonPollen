package com.example.demo.bean;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.example.demo.service.APIContext;

@Configuration
public class PropertiesConfiguration {

	@Bean
	public APIContext apiContext() {
		return new APIContext();
	}
	
}
