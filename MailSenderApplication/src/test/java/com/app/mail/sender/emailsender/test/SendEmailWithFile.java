package com.app.mail.sender.emailsender.test;

import java.io.File;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.app.mail.sender.service.EmailService;

@SpringBootTest
public class SendEmailWithFile {

	@Autowired
	private EmailService emailService;
	@Test
	void emailSendWithFileTest() {
		System.out.println("Sending email...");
		emailService.sendEmailWithFile("mantukumar4376@gmail.com", 
				"Email From Spring Boot With File ", 
				"This email contains file",
//				new File("/D:/MailSenderSpringBoot_React_Project/MailSenderApplication/src/main/resources/static/Images/male.png") );
				new File("/D:/MailSenderSpringBoot_React_Project/MailSenderApplication/src/main/resources/static/Images/myImage.jpg") );

		
		
	}
}
