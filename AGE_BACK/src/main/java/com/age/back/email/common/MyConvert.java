package com.age.back.email.common;

import java.io.Serializable;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.Session;

import org.apache.activemq.command.ActiveMQObjectMessage;
import org.springframework.jms.support.converter.MessageConversionException;
import org.springframework.jms.support.converter.MessageConverter;

import com.age.back.sysmng.model.SysEmail;

/**  
 *   
 * 消息转换器  
 *   
 * @author zhouhua, 2012-12-25  
 */
public class MyConvert implements MessageConverter {

	/**  
	 * @param arg0  
	 * @return  
	 * @throws JMSException  
	 * @throws MessageConversionException  
	 * @see org.springframework.jms.support.converter.MessageConverter#fromMessage(javax.jms.Message)  
	 */
	@Override
	public Object fromMessage(Message message) throws JMSException, MessageConversionException {
		SysEmail email = null;
		if (message instanceof ActiveMQObjectMessage) {
			ActiveMQObjectMessage aMsg = (ActiveMQObjectMessage) message;
			email = (SysEmail) aMsg.getObject();
		}
		return email;
	}

	/**  
	 * @param arg0  
	 * @param arg1  
	 * @return  
	 * @throws JMSException  
	 * @throws MessageConversionException  
	 * @see org.springframework.jms.support.converter.MessageConverter#toMessage(java.lang.Object, javax.jms.Session)  
	 */
	@Override
	public Message toMessage(Object object, Session session) throws JMSException, MessageConversionException {

		ActiveMQObjectMessage msg = (ActiveMQObjectMessage) session.createObjectMessage();
		msg.setObject((Serializable) object);
		return msg;
	}

}