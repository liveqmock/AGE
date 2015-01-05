package com.age.back.email.listener;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.ObjectMessage;

import org.springframework.beans.factory.annotation.Autowired;

import com.age.back.email.common.EmailConfig;
import com.age.back.email.service.IReceiveMailService;
import com.age.back.sysmng.model.SysEmail;

public class ReceiveMailMessageListener implements MessageListener {
	@Autowired
	private IReceiveMailService receiveMailService;

	private EmailConfig connfig = new EmailConfig();

	@Override
	public void onMessage(Message message) {
		if (message instanceof ObjectMessage) {
			ObjectMessage objectMessage = (ObjectMessage) message;
			try {
				SysEmail em = (SysEmail) objectMessage.getObject();
				receiveMailService.receiveMail(em, connfig);
			} catch (JMSException e) {
				e.printStackTrace();
			}
		}

	}
}
