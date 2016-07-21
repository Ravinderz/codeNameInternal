package com.congun.web.util;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;


public class ApplicationUtil {

	public static String getJsonResponse(Object obj){
		
		if(obj != null){
			Gson gs = new GsonBuilder().create();
			return gs.toJson(obj);
		}else
			return "User doesnot Exist";
	}
	
	public static Date formatDate(Date date) throws ParseException{
		DateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
		String myDate;
		System.out.println("Date :"+date);
		myDate = formatter.format(date);
		System.out.println(myDate);
		return formatter.parse(myDate);
		
	}
}
