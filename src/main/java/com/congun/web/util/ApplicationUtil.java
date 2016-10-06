package com.congun.web.util;

import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.log4j.Logger;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.congun.common.EmailClient;
import com.congun.common.EmailModel;
import com.congun.web.dao.SupplierQuoteDao;
import com.congun.web.dao.UserDao;
import com.congun.web.model.User;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

@Component
public class ApplicationUtil {
	private static Logger logger = Logger.getLogger(ApplicationUtil.class);

	@Autowired
	public UserDao userdao;

	@Autowired
	public SupplierQuoteDao supplierDao;

	private static final String DEFAULT_PRNG = "SHA1PRNG"; // algorithm to

	// generate key

	public ApplicationUtil() {
	}

	public ApplicationUtil(UserDao userdao) {
		this.userdao = userdao;
	}

	public Timestamp getCurrentTimeStamp() {
		return new Timestamp(new Date().getTime());
	}

	public String getToken() throws NoSuchAlgorithmException {
		SecureRandom sr = SecureRandom.getInstance(DEFAULT_PRNG);
		return "" + sr.nextLong();
	}

	public static String getJsonResponse(Object obj) {
		logger.info("Entered into ApplicationUtil.getJsonResponse method");

		if (obj != null) {
			Gson gs = new GsonBuilder().create();
			return gs.toJson(obj);
		} else
			return "User doesnot Exist";
	}

	public static Date formatDate(Date date) throws ParseException {
		logger.info("Entered into ApplicationUtil.formatDate method date:"
				+ date);
		DateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
		String myDate;
		myDate = formatter.format(date);
		return formatter.parse(myDate);

	}

	public void sendReqEmailToSupp(long suppId, long reqId) {
		// int supplierId = 6;
		// get email of supplier from supplier table . Need to add API for this
		// String emailId = "nishant.vengala@gmail.com";
		logger.info("Entered into sendReqEmailToSupp method  suppID:" + suppId
				+ "reqId:" + reqId);
		if (userdao == null)
			System.out.println("NULLLLLLLLLLLLLLL");
		else
			System.out.println("NOT NULLLL");
		User user = userdao.getUserById(suppId);
		String emailId = user.getUsername();
		EmailModel eModel = new EmailModel();
		// eModel.setFrom("nishant.vengala@gmail.com");
		eModel.setFrom("admin@congun.com");
		eModel.setTo(emailId);
		eModel.setSubject("New Requirement Mapped");
		eModel.setMsg("Hi you have receved new requirement !!!");
		EmailClient eClient = new EmailClient();

		eClient.sendEmail(eModel);

	}

	public void sendQuoteEmailToContractor(String contractorEmail, String Name,
			String mobileNo, String title) {
		logger.info("Sending mail to contractor");
		String emailId = contractorEmail;
		EmailModel eModel = new EmailModel();
		// eModel.setFrom("nishant.vengala@gmail.com");
		eModel.setFrom("admin@congun.com");
		eModel.setTo(emailId);
		eModel.setSubject("Quotation for your '" + title + "' requirement");
		eModel.setMsg("Hi you have receved new Quotation !!!\n"
				+ "Supplier Name:" + Name + "\n\n" + "MobileNo:" + mobileNo);
		EmailClient eClient = new EmailClient();

		eClient.sendEmail(eModel);

	}

	public JSONObject composeJsonOuput(String errorCode,
			String errorMessage) {
		JSONObject jsonObject = new JSONObject();
		jsonObject.put(ResponseConstants.ERROR_CODE, errorCode);
		jsonObject.put(ResponseConstants.ERROR_MESSAGE, errorMessage);
		return jsonObject;
	}
}
