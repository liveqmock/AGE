package com.age.clms.service;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.transaction.TransactionConfiguration;

import com.age.core.test.spring.SpringTxTestCase;

@ContextConfiguration(locations = { "/applicationContext-test.xml" })
@TransactionConfiguration(transactionManager = "transactionManager", defaultRollback = true)
public class ServiceTest extends SpringTxTestCase {
	private final Logger logger = LoggerFactory.getLogger(getClass());

	@Test
	public void testQuery() {
		//EmailUtil.getSendMailService().sendMessage("dddddddddddd");

		//EmailUtil.getSendMailService().receiveMessage();
	}
}
