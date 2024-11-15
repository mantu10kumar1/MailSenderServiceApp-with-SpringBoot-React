package com.app.mail.sender.emailsender.test;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.app.mail.sender.service.EmailService;

@SpringBootTest
public class SendEmailWithHtmlContent {

	@Autowired
	private EmailService emailService;
	@Test
	void emailSendWithHtmlContentTest() {
		
		String html = ""+
		                 "<h1 style='color:red; border:1px solid red;' >Welcome To The World of Email Sending.. </h1>"
			          +"";
		
		System.out.println("Sending email...");
		emailService.sendEmailWithHtml("mantukumar4376@gmail.com", "Email From Spring Boot With HTML Content", html);
		
		
	}
}
