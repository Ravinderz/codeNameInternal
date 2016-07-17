package com.congun.web.util;

	import com.google.gson.Gson;
	import com.google.gson.GsonBuilder;


	public class ApplicationUtil {

		public static String getJsonResponse(Object obj){
			
			if(obj != null){
				Gson gs = new GsonBuilder().create();
				return gs.toJson(obj);
			}else
				return "Object returned is null";
		}
	}
