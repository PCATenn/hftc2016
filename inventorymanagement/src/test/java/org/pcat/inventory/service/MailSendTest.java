package org.pcat.inventory.service;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@ContextConfiguration(locations = { "classpath:test-serviceContext.xml" })
public class MailSendTest {

	@Autowired
	private MailService mailService;

	@Test
	public void sendHomeUserEmailIntegrationTest() {

		String message = "Requesting supplies for family DAVI-0001";
		String subject = "Subj:  Supplies for family DAVI-0001";
		mailService.sendMail("testFrom.pcat@mailinator.com", "testTo.pcat@mailinator.com", "testCc.pcat@mailinator.com",
				subject, message);
	}

}
