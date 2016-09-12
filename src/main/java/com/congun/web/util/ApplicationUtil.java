package com.congun.web.util;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.log4j.Logger;

import com.congun.web.dao.ContractorRequirementQuoteDAO;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;


public class ApplicationUtil {
	private static Logger logger = Logger.getLogger(ApplicationUtil.class);

	public static String getJsonResponse(Object obj){
		logger.info("Entered into ApplicationUtil.getJsonResponse method");
		
		if(obj != null){
			Gson gs = new GsonBuilder().create();
			return gs.toJson(obj);
		}else
			return "User doesnot Exist";
	}
	
	public static Date formatDate(Date date) throws ParseException{
		logger.info("Entered into ApplicationUtil.formatDate method date:"+date);
		DateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
		String myDate;
		myDate = formatter.format(date);
		return formatter.parse(myDate);
		
	}
}
