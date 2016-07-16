package com.congun.web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.congun.web.model.SupplierQuote;
import com.congun.web.service.SupplierQuoteService;

@RestController
@RequestMapping("supplier")
public class SupplierController {

	@Autowired
	SupplierQuoteService supplierService;
	
	@RequestMapping(value="/submitquote" , method=RequestMethod.POST)
	public 	String submitQuotation(@RequestBody SupplierQuote supplierQuotation){
		System.out.println("Posting Requirement details from Supplier");
		return supplierService.submitQuote(supplierQuotation);
		
	}
}
