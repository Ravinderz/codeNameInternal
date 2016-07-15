package com.congun.web.util;


import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class GenerateHash {
	
	public static String getHash(String convert){
		
		return MD5(convert);
	}

		public static String MD5(String convert){
		
		String md5 = null;
		String salt="Thisi$Congun#SaltMD5";
		convert = convert+salt;
		if(convert.equals(null)){
			return null;
		} try{
			MessageDigest digest = MessageDigest.getInstance("MD5");
			digest.update(convert.getBytes(), 0, convert.length());
			md5 = new BigInteger(1,digest.digest()).toString(16);
		}catch(NoSuchAlgorithmException e){
			e.printStackTrace();
		}
		return md5;
	}
	
	
	}

	

	

