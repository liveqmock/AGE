package com.age.back.email.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.age.back.email.common.EmailConfig;
import com.age.back.email.common.SendEmailUtil;
import com.age.back.email.service.IReceiveMailService;
import com.age.back.email.vo.EmailVo;
import com.age.back.sysmng.dao.SysEmailDao;
import com.age.back.sysmng.model.SysEmail;

@Service
public class ReceiveMailService implements IReceiveMailService {
	@Autowired
	private SysEmailDao sysEmailDao;

	@Override
	public void receiveMail(SysEmail mail, EmailConfig config) {
		boolean result = SendEmailUtil.sendMail(mail, config);
		if (result) {
			sysEmailDao.updateSendFlag("1", mail.getId());
		} else {
			sysEmailDao.updateSendFlag("2", mail.getId());
		}
	}
}
