package com.app.mail.sender.emailsender.test;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.app.mail.sender.service.EmailService;

@SpringBootTest
public class EmailSenderToSinglePersonTest {

	@Autowired
	private EmailService emailService;
	@Test
	void emailSendToSinglePersonTest() {
		System.out.println("Sending email...");
		emailService.sendEmail("mantukumar4376@gmail.com", "Email From Spring Boot", "This email is send using spring boot while creating email service.");
		
		
	}
}
