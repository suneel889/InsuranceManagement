package com.example.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.example.entity.InsuranceDetails;

public interface InsurancePlanRepo extends JpaRepository<InsuranceDetails, Integer> {
@Query(value="select distinct plan_name from insurance_details",nativeQuery = true)
	public List<String> getPlanNames();
@Query(value="select distinct plan_status from insurance_details",nativeQuery = true)
public List<String> getPlanStatus();

}
