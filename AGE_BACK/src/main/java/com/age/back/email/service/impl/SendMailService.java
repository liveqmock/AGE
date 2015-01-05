package com.age.back.email.service.impl;

import javax.jms.Destination;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.Session;
import javax.jms.TextMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.core.MessageCreator;

import com.age.back.email.common.EmailConfig;
import com.age.back.email.service.ISendMailService;
import com.age.back.email.vo.EmailVo;
import com.age.back.sysmng.dao.SysEmailDao;
import com.age.back.sysmng.model.SysEmail;
import com.age.core.utils.DateUtils;
import com.age.core.utils.IdUtils;

public class SendMailService implements ISendMailService {

	private JmsTemplate jmsTemplate;
	private Destination destination;
	@Autowired
	private SysEmailDao sysEmailDao;

	private EmailConfig email_config = new EmailConfig();

	public JmsTemplate getJmsTemplate() {
		return jmsTemplate;
	}

	public void setJmsTemplate(JmsTemplate jmsTemplate) {
		this.jmsTemplate = jmsTemplate;
	}

	public Destination getDestination() {
		return destination;
	}

	public void setDestination(Destination destination) {
		this.destination = destination;
	}

	public void sendMessage(final String message) {
		//JmsTemplate jmsTemplate = SpringContextHolder.getBean("jmsTemplate");
		jmsTemplate.send(destination, new MessageCreator() {
			@Override
			public Message createMessage(Session session) throws JMSException {
				TextMessage textMessage = session.createTextMessage(message);
				return textMessage;
			}
		});
	}

	public void sendObjectMessage(final EmailVo om) {
		try {
			SysEmail se = new SysEmail();
			se.setId(IdUtils.uuid32());
			se.setContent(om.getContent());
			se.setReceiverAddress(om.getReceiverAddress());
			se.setReceiverName(om.getReceiverName());
			se.setSubject(om.getSubject());
			se.setSenderAddress(email_config.getEmail_account());
			se.setSenderName(email_config.getEmail_name());
			se.setCreateTime(DateUtils.currentDatetime());
			se.setSendTime(DateUtils.currentDatetime());
			se.setSendFlag("0");
			sysEmailDao.saveOrUpdate(se);
			jmsTemplate.convertAndSend(destination, se);
		} catch (Exception e) {
		}

	}
}
