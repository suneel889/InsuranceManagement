package com.example.dto;

import java.util.UUID;

import lombok.Data;

@Data
public class InsuranceDetailsDto {

private Integer planId;
	
	private String planName;
	private String holderName;
	private Long holderSSN;
	private String planStatus;
}
