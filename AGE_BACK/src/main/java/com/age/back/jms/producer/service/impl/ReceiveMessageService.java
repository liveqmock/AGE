package com.age.back.jms.producer.service.impl;

import javax.jms.Destination;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.ObjectMessage;
import javax.jms.Session;

import org.apache.activemq.ScheduledMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.core.MessageCreator;
import org.springframework.stereotype.Service;

import com.age.back.jms.message.Id;
import com.age.back.jms.producer.service.MessageService;

@Service
@Qualifier("Receive")
public class ReceiveMessageService implements MessageService {
	@Autowired
	private JmsTemplate jmsTemplate;
	@Autowired
	@Qualifier("clms_receiveTable")
	private Destination destination;

	@Override
	public void send(final Id<?> id) {
		jmsTemplate.send(destination, new MessageCreator() {
			@Override
			public Message createMessage(Session session) throws JMSException {
				ObjectMessage objectMessage = session.createObjectMessage(id);
				long time = 5 * 1000; //延迟发送 5 秒 持久化消息
				objectMessage.setLongProperty(ScheduledMessage.AMQ_SCHEDULED_DELAY, time);
				return objectMessage;
			}
		});
	}

}
