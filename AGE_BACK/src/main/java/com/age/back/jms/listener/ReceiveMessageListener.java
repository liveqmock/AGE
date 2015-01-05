package com.age.back.jms.listener;

import java.io.Serializable;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.ObjectMessage;

import com.age.back.jms.message.Id;

public class ReceiveMessageListener implements MessageListener{
	@Override
	public void onMessage(Message message) {
		if (message instanceof ObjectMessage) {
			ObjectMessage objectMessage = (ObjectMessage) message;
			try {
				Serializable object = objectMessage.getObject();
				if(object instanceof Id){
					Id<String> idObject = (Id) object;
					String id = idObject.getId();
				}
			} catch (JMSException e) {
				e.printStackTrace();
			}
		}
	}

}
