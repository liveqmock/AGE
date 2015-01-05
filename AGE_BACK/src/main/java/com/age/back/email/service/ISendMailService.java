package com.age.back.email.service;

import com.age.back.email.vo.EmailVo;

public interface ISendMailService {
	public void sendMessage(final String message);

	public void sendObjectMessage(final EmailVo om);
}
