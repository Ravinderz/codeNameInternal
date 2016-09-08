package com.congun.common;

public class EmailModel {
	
	private String to;
	
	private String from;
	
	private String ccRM;
	
	private String subject;
	
	private String msg;
	

	public String getCcRM() {
		return ccRM;
	}

	public void setCcRM(String ccRM) {
		this.ccRM = ccRM;
	}

	public String getTo() {
		return to;
	}

	public void setTo(String to) {
		this.to = to;
	}

	public String getFrom() {
		return from;
	}

	public void setFrom(String from) {
		this.from = from;
	}

	public String getSubject() {
		return subject;
	}

	public void setSubject(String subject) {
		this.subject = subject;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	


}
