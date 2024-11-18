package com.app.mail.sender;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class MailSenderApplication {

	public static void main(String[] args) {
		SpringApplication.run(MailSenderApplication.class, args);
		System.out.println("\n\t\t\t\t\tOur Application MailSender is running...\n");
	}

}
