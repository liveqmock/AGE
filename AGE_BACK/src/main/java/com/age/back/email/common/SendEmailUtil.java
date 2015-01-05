package com.age.back.email.common;

import org.apache.commons.mail.EmailException;
import org.apache.commons.mail.SimpleEmail;

import com.age.back.email.vo.EmailVo;
import com.age.back.sysmng.model.SysEmail;

public class SendEmailUtil {

	//发送邮件
	public static boolean sendMail(SysEmail se, EmailConfig config) {
		boolean result = false;
		SimpleEmail email = new SimpleEmail();
		try {
			//发送邮件通知
			email.setHostName(config.getEmail_host());
			email.setCharset("UTF-8");
			email.setAuthentication(config.getEmail_account(), config.getEmail_passWord());
			email.addTo(se.getReceiverAddress(), se.getReceiverName());
			email.setFrom(se.getSenderAddress(), se.getSenderName());
			email.setSubject(se.getSubject());
			email.setMsg(se.getContent());
			email.send();
			result = true;
		} catch (EmailException e) {
			result = false;
			e.printStackTrace();
		}
		return result;

	}
}
