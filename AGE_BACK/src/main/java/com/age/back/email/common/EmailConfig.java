package com.age.back.email.common;

import com.age.core.utils.spring.SpringPropertyConfigurer;

public class EmailConfig {

	public java.lang.String getEmail_host() {
		return email_host;
	}

	public void setEmail_host(java.lang.String emailHost) {
		email_host = emailHost;
	}

	public java.lang.String getEmail_account() {
		return email_account;
	}

	public void setEmail_account(java.lang.String emailAccount) {
		email_account = emailAccount;
	}

	public java.lang.String getEmail_passWord() {
		return email_passWord;
	}

	public void setEmail_passWord(java.lang.String emailPassWord) {
		email_passWord = emailPassWord;
	}

	public java.lang.String getEmail_name() {
		return email_name;
	}

	public void setEmail_name(java.lang.String emailName) {
		email_name = emailName;
	}

	public java.lang.String getEmail_port() {
		return email_port;
	}

	public void setEmail_port(java.lang.String emailPort) {
		email_port = emailPort;
	}

	private String email_host = SpringPropertyConfigurer.getProperty("mail.host");
	private String email_account = SpringPropertyConfigurer.getProperty("mail.username");
	private String email_passWord = SpringPropertyConfigurer.getProperty("mail.password");
	private String email_name = SpringPropertyConfigurer.getProperty("mail.name");
	private String email_port = SpringPropertyConfigurer.getProperty("mail.port");

}
