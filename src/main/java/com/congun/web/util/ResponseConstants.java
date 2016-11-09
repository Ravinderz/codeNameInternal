package com.congun.web.util;

public class ResponseConstants {
	
	public static final String USER_SUCCESS_CODE = "US01";
	public static final String USER_FAILURE_CODE = "UF01";
	public static final String USER_EXCEPTION_CODE = "UE01";
	public static final String USER_FORGET_PASSWORD_SUCCESS_CODE = "UPS01 : password successfully updated";
	public static final String USER_FORGET_PASSWORD_EXCEPTION_CODE = "UPE01 : password updation failed";
	public static final String INVALID_USER_EXCEPTION_CODE = "ICE01";
	public static final String INVALID_USER_EXCEPTION_MESSAGE = "Invalid Credentials";
	public static final String USER_FORGET_PASSWORD_EMAIL_SUCCESS_CODE = "UPES01 : Email successfully sent";
	public static final String USER_FORGET_PASSWORD_EMAIL_EXCEPTION_CODE = "UPEE01 : Email failed";
	
	
	public static final String CONTRACTOR_SUCCESS_CODE = "CS01";
	public static final String CONTRACTOR_FAILURE_CODE = "CF01";
	public static final String CONTRACTOR_EXCEPTION_CODE = "CE01";
	

	public static final String SUPPLIER_SUCCESS_CODE = "SS01";
	public static final String SUPPLIER_FAILURE_CODE = "SF01";
	public static final String SUPPLIER_EXCEPTION_CODE = "SE01";
	

	public static final String EQUIPMENT_SUCCESS_CODE = "ES01";
	public static final String EQUIPMENT_FAILURE_CODE = "EF01";
	public static final String EQUIPMENT_EXCEPTION_CODE = "EE01";
	

	public static final String MACHINE_SUCCESS_CODE = "MS01";
	public static final String MACHINE_FAILURE_CODE = "MF01";
	public static final String MACHINE_EXCEPTION_CODE = "ME01";
	
	public static final String WRONG_PASSWORD_CODE = "WPSD";
	
	// AUTHENTICATION INTERCEPTOR
	public static final String AUTH_SUCCESS_CODE = "AS01";
	public static final String AUTH_FAILURE_CODE = "AF01";
	public static final String UNAUTHORISED_SERVICE_CALL = "Unauthorised Service Call";
	public static final String AUTHENTICATION_FAILED_STRING = "Authentication Failed";
	
	public static final String USR_LOGOUT_TOKEN_ERR_CODE = "UF02";
	public static final String USR_LOGOUT_TOKEN_ERR_MSG = "User session inatives";
	
	public static final String ERROR_CODE = "errorCode";
	public static final String ERROR_MESSAGE = "errorMsg";
	
	public static final String UPLOAD_SUCCESS = "UP01";
	public static final String UPLOAD_FAILURE = "UF01";
	public static final String UPLOAD_SUCCESS_MESSAGE = "Uploaded Successfully";
	public static final String UPLOAD_FAILURE_MESSAGE= "Upload Failed";
	
	//Contact us
		public static final String CONTACT_US_SUCCESS = "CUS01";
		public static final String CONTACT_US_EXP = "CUF01";
		public static final String[] QRY_MAIL_TO = {"nv00334638@techmahindra.com","nishant.vengala@gmail.com"};
		public static final String MAIL_FOOTER = "<br><br><br>Regards,<br>Congun Support Team";
		public static final String DO_NOT_REPLY_MSG="<br>******* This is an auto-generated mail. Please do not reply********<br><br>";
	
}
