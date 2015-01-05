package com.age.back.email.util;

import com.age.back.email.service.ISendMailService;
import com.age.back.email.vo.EmailVo;
import com.age.back.sysmng.model.SysEmail;
import com.age.core.utils.spring.SpringContextHolder;

public class EmailUtil {

	private static ISendMailService getSendMailService() {
		return (ISendMailService) SpringContextHolder.getBean("sendMailService");
	}

	public static void sendObjectMessage(final EmailVo evo) {
		getSendMailService().sendObjectMessage(evo);
	}
}
