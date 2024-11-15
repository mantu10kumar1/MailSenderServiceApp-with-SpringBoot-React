package com.app.mail.sender.emailsender.test;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.app.mail.sender.service.EmailService;

@SpringBootTest
public class SendEmailWithInputFileStream {

	@Autowired
	private EmailService emailService;
	@Test
	void emailSendWithFileInputStreamTest() {
		System.out.println("Sending email...");
		
		File file = new File("/D:/MailSenderSpringBoot_React_Project/MailSenderApplication/src/main/resources/static/Images/myImage.jpg");
		
		try {
			
			InputStream is = new FileInputStream(file);
			
			emailService.sendEmailWithInputStreamFile("mantukumar4376@gmail.com", 
					"Email From Spring Boot With File ", 
					"This email contains file",is);
		} catch(FileNotFoundException e) {
			throw new RuntimeException(e);
		}
		
		
	}
}
