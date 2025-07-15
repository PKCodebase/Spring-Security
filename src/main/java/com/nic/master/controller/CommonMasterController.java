package com.nic.master.controller;

import java.util.Date;
import java.util.List;



import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.nic.master.param.SelectOptionParam;
import com.nic.master.param.error.ApiError;
import com.nic.master.service.CommonMasterService;

import jakarta.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/common")
@RequiredArgsConstructor
@Tag(name = "Common Master API", description = "APIs for managing common master data")
public class CommonMasterController {


	private final CommonMasterService commonMasterService;


	// API to fetch common master data based on table code
	@GetMapping("/get")
	public ResponseEntity<Object> getObpsMasters(@RequestParam("tcode") String tableCode,
			  HttpServletRequest httpServletRequest)
	{
		
		try {
				List<SelectOptionParam> masters = commonMasterService.fetchCommonMaster(tableCode);
				return ResponseEntity.ok(masters);
				
		} catch (Exception e) {
			e.printStackTrace();
			HttpStatus httpStatus = HttpStatus.UNPROCESSABLE_ENTITY;
			return ResponseEntity.status(httpStatus).body(new ApiError(new Date(), 
						String.valueOf(httpStatus.value()), httpStatus.getReasonPhrase(), 
						httpServletRequest.getRequestURI(), "", "" ));
		}
	}


	// API to fetch common master data based on table code and code value
	@GetMapping("/getByCode")
	public ResponseEntity<Object> getObpsMasters(@RequestParam("tcode") String tableCode,
			@RequestParam("codeVal") String codeVal,
			  HttpServletRequest httpServletRequest)
	{
		
		try {
				SelectOptionParam master = commonMasterService.fetchCommonMasterByCode(tableCode, codeVal);
				return ResponseEntity.ok(master);
				
		} catch (Exception e) {
			e.printStackTrace();
			HttpStatus httpStatus = HttpStatus.UNPROCESSABLE_ENTITY;
			return ResponseEntity.status(httpStatus).body(new ApiError(new Date(), 
						String.valueOf(httpStatus.value()), httpStatus.getReasonPhrase(), 
						httpServletRequest.getRequestURI(), "", "" ));
		}
																																																																							}
    // API to add a new service






	// LIST ALL ACTIVE RECORDS 
	
	//POST SAVE METHOD
	
	//UPDATE //
	
	// API to make record inactive. Table row's isActive flag will be inactive/false
}
