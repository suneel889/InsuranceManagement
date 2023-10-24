package com.example.service;

import java.util.List;
import com.example.dto.InsuranceDetailsDto;
import com.example.dto.InsurenceRequestDto;
import com.example.entity.InsuranceDetails;


public interface InsuranceDetailsServiceInterface {

	
	  public List<String> gettAllPlanNames();
	  public List<String> getAllPlanStatus();
	  public List<InsuranceDetailsDto> serchRegisteredMembers(InsurenceRequestDto request);
	  public List<InsuranceDetails> listAll();
	  
}
