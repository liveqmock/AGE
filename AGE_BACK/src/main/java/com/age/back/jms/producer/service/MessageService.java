package com.age.back.jms.producer.service;

import com.age.back.jms.message.Id;


public interface MessageService {
	public void send(final Id<?> id);
}
