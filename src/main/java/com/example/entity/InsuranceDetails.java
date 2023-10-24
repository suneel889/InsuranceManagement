package com.example.entity;

import java.util.UUID;

import javax.annotation.Generated;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;



@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class InsuranceDetails {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name="plan_id")
	private Integer planId;
	@Column(name="plan_name")
	private String planName;
	@Column(name="holder_name")
	private String holderName;
	@Column(name="holder_ssn")
	private Long holderSSN;
	@Column(name="plan_status")
	private String planStatus;
	

}
