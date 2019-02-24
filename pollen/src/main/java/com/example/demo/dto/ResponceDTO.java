package com.example.demo.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ResponceDTO {
	String weather;
	Integer humidity;
	double speed;
}
