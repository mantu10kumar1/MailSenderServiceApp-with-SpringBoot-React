package com.app.mail.sender.emailsender.test;

import java.io.IOException;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.app.mail.sender.service.EmailService;

import jakarta.mail.Message;

@SpringBootTest
public class RecevingEmailTest {
	
	@Autowired
	private EmailService emailService;
	// Testing of Receiving emails

	@Test
	public void getInbox() throws IOException {
		List<com.app.mail.sender.emailmodel.Message> inboxMessages = emailService.getInboxMessages();
		inboxMessages.forEach(item ->{
			System.out.println(item.getSubjects());
			System.out.println(item.getContent());
			System.out.println(item.getFiles());
			System.out.println("___________________");


			
		});
		
	}
}
