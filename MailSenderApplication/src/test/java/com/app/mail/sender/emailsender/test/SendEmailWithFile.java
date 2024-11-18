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
//		emailService.sendEmailWithFile("mantukumar4376@gmail.com", 
		emailService.sendEmailWithFile("220101120006@cutm.ac.in", 

				"For Course ", 
				"You have to pay fine of 10000 rs",
//				new File("/D:/MailSenderSpringBoot_React_Project/MailSenderApplication/src/main/resources/static/Images/male.png") );
				new File("/D:/MailSenderSpringBoot_React_Project/MailSenderApplication/src/main/resources/static/Images/myImage.jpg") );

		
		
	}
}
