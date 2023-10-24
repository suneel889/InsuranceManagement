package com.example.service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Service;

import com.example.dto.InsuranceDetailsDto;
import com.example.dto.InsurenceRequestDto;
import com.example.entity.InsuranceDetails;
import com.example.repo.InsurancePlanRepo;


@Service
public class InsuranceDetailsServiceImplementation implements InsuranceDetailsServiceInterface {
  
	@Autowired
	private InsurancePlanRepo repo;
	
	
	@Override
	public List<String> gettAllPlanNames() {
		
		//List<String> planNames= repo.findAll().stream().map(x->x.getPlanName()).distinct().collect(Collectors.toList());
		System.out.println("hi");
		return repo.getPlanNames();
	}

	@Override
	public List<String> getAllPlanStatus() {
		// TODO Auto-generated method stub
		//return repo.findAll().stream().map(x->x.getPlanStatus()).distinct().collect(Collectors.toList());
		return repo.getPlanStatus();
		}

	@Override
	public List<InsuranceDetailsDto> serchRegisteredMembers(InsurenceRequestDto request) {
		InsuranceDetails insuranceEntity=new InsuranceDetails();
		if(request.getPlanName() != null && ! request.getPlanName().equals("") )
			insuranceEntity.setPlanName(request.getPlanName());
		if(request.getPlanStatus() != null && !request.getPlanStatus().equals("") )
			insuranceEntity.setPlanName(request.getPlanName());
			
		Example<InsuranceDetails> of = Example.of(insuranceEntity);
		List <InsuranceDetailsDto> result=new ArrayList<>();
		repo.findAll(of).forEach(x->{
			InsuranceDetailsDto insuranceDto=new InsuranceDetailsDto();
			insuranceDto.setHolderName(x.getHolderName());
			insuranceDto.setHolderSSN(x.getHolderSSN());
			insuranceDto.setPlanId(x.getPlanId());
			insuranceDto.setPlanStatus(x.getPlanStatus());
			insuranceDto.setPlanName(x.getPlanName());
			result.add(insuranceDto);		
		});
		return result;
	}
	 public List<InsuranceDetails> listAll() {
	        return repo.findAll();
	    }

}
