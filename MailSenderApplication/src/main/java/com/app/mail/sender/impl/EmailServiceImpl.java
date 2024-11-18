package com.app.mail.sender.impl;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.FileSystemResource;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import com.app.mail.sender.repository.EmailRepository;
import com.app.mail.sender.service.EmailService;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
@Service
public class EmailServiceImpl implements EmailService{

	private Logger logger = LoggerFactory.getLogger(EmailServiceImpl.class);
	
//	@Autowired
//	private JavaMailSender mailSender;
	// OR , We can create constructor of JavaMailSender instead of @Autowired
	
//	private EmailRepository emailRepo;
	private JavaMailSender mailSender;
	
	public EmailServiceImpl(JavaMailSender mailSender) {
	super();
	this.mailSender = mailSender;
}

	// Send mail to the single person
	@Override
	public void sendEmail(String to, String subject, String message) {
		SimpleMailMessage simpleMailMessage =  new SimpleMailMessage();
		simpleMailMessage.setTo(to);
		simpleMailMessage.setSubject(subject);
		simpleMailMessage.setText(message);
		simpleMailMessage.setFrom("mantudemo8@gmail.com");
		mailSender.send(simpleMailMessage);
		logger.info("Email has been send to the single person..");
	}

	// Sending mail to the multiple person
	@Override
	public void sendEmail(String[] to, String subject, String message) {
		SimpleMailMessage simpleMailMessage =  new SimpleMailMessage();
		simpleMailMessage.setTo(to);
		simpleMailMessage.setSubject(subject);
		simpleMailMessage.setText(message);
		simpleMailMessage.setFrom("mantudemo8@gmail.com");
		mailSender.send(simpleMailMessage);
		logger.info("Email has been send to the multiple persons...");
	}

	// Sending mail with html contents
	@Override
	public void sendEmailWithHtml(String to, String subject, String htmlContent) {
		
		MimeMessage simpleMailMessage = mailSender.createMimeMessage();
		
		
		try {
			
			MimeMessageHelper helper = new MimeMessageHelper(simpleMailMessage , true , "UTF-8");
			helper.setTo(to);
			helper.setSubject(subject);
			helper.setFrom("mantudemo8@gmail.com");
			helper.setText(htmlContent, true);
			mailSender.send(simpleMailMessage);
			logger.info("Email has been send with html content..");
			
		} catch(MessagingException e) {
			throw new RuntimeException(e);
		}

		
	}

    // Send Email with file
	@Override
	public void sendEmailWithFile(String to, String subject, String message, File file) {
		
		MimeMessage mimeMessage = mailSender.createMimeMessage();
		
		try {
			MimeMessageHelper helper = new MimeMessageHelper(mimeMessage , true);
			helper.setFrom("mantudemo8@gmail.com");
			helper.setTo(to);
			helper.setText(message);
			helper.setSubject(subject);
			FileSystemResource fileSystemResource = new FileSystemResource(file);
			helper.addAttachment(fileSystemResource.getFilename(), file);
			mailSender.send(mimeMessage);
			logger.info("Email Send successfully with file...");
		} catch(MessagingException e) {
			throw new RuntimeException(e);
		}
	}

	// Send email with InputStream file
	@Override
	public void sendEmailWithInputStreamFile(String to, String subject, String message, InputStream is) {
	MimeMessage mimeMessage = mailSender.createMimeMessage();
		
		try {
			MimeMessageHelper helper = new MimeMessageHelper(mimeMessage , true);
			helper.setFrom("mantudemo8@gmail.com");
			helper.setTo(to);
			helper.setText(message,true);
			helper.setSubject(subject);
			File file = new File("src/main/resources/email/test.png");
			Files.copy(is, file.toPath(), StandardCopyOption.REPLACE_EXISTING);
			
			FileSystemResource fileSystemResource = new FileSystemResource(file);
			helper.addAttachment(fileSystemResource.getFilename(), file);
			mailSender.send(mimeMessage);
			logger.info("Email Send successfully with file InputStream file...");
		} catch(MessagingException e) {
			throw new RuntimeException(e);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
