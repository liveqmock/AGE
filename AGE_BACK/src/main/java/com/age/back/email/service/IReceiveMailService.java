package com.age.back.email.service;

import com.age.back.email.common.EmailConfig;
import com.age.back.email.vo.EmailVo;
import com.age.back.sysmng.model.SysEmail;

public interface IReceiveMailService {
	public void receiveMail(SysEmail mail, EmailConfig config);
}
