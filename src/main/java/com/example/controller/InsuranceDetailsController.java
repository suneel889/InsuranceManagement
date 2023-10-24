package com.example.controller;

import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.dto.InsuranceDetailsDto;
import com.example.dto.InsurenceRequestDto;
import com.example.entity.InsuranceDetails;
import com.example.formgenerations.UserExcelExporter;
import com.example.formgenerations.UserPDFExporter;
import com.example.repo.InsurancePlanRepo;
import com.example.service.InsuranceDetailsServiceInterface;
import com.lowagie.text.DocumentException;



@RestController
@RequestMapping("/insurance")
public class InsuranceDetailsController {
	
	@Autowired
	InsuranceDetailsServiceInterface service;
	
	@Autowired
	InsurancePlanRepo repo;
	
	
	@PostMapping("/save")
	public String save(@RequestBody InsuranceDetailsDto request) {
		InsuranceDetails d1=new InsuranceDetails();
		BeanUtils.copyProperties(request, d1);
		repo.save(d1);
		return "sucessfully registered";
		
	}
	
	@PostMapping("search")
	public ResponseEntity<List<InsuranceDetailsDto>> serchPlans(@RequestBody InsurenceRequestDto request){
		return new ResponseEntity<>(service.serchRegisteredMembers(request),HttpStatus.OK);
	}
	@GetMapping("plannames")
	public ResponseEntity<List<String>> getAllPlanNames(){
		return new ResponseEntity<>(service.gettAllPlanNames(),HttpStatus.OK);
	}
	@GetMapping("plansstatus")
	public ResponseEntity<List<String>> getAllPlanStatus(){
		return new ResponseEntity<>(service.getAllPlanStatus(),HttpStatus.OK);
	}
	@GetMapping("/excel")
    public void exportToExcel(HttpServletResponse response) throws IOException {
        response.setContentType("application/octet-stream");
        DateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-dd_HH:mm:ss");
        String currentDateTime = dateFormatter.format(new Date());
         
        String headerKey = "Content-Disposition";
        String headerValue = "attachment; filename=users_" + currentDateTime + ".xlsx";
        response.setHeader(headerKey, headerValue);
         
        List<InsuranceDetails> listUsers = service.listAll();
         
        UserExcelExporter excelExporter = new UserExcelExporter(listUsers);
         
        excelExporter.export(response);    
    }
	 @GetMapping("/pdf")
	    public void exportToPDF(HttpServletResponse response) throws DocumentException, IOException {
	        response.setContentType("application/pdf");
	        DateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-dd_HH:mm:ss");
	        String currentDateTime = dateFormatter.format(new Date());
	         
	        String headerKey = "Content-Disposition";
	        String headerValue = "attachment; filename=users_" + currentDateTime + ".pdf";
	        response.setHeader(headerKey, headerValue);
	         
	        List<InsuranceDetails> listUsers = service.listAll();
	         System.out.println(listUsers);
	        UserPDFExporter exporter = new UserPDFExporter(listUsers);
	        exporter.export(response);
	         
	    }
	

}
