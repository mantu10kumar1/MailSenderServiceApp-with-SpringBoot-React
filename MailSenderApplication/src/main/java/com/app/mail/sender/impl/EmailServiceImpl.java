package com.app.mail.sender.impl;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.FileSystemResource;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import com.app.mail.sender.emailmodel.Message;
import com.app.mail.sender.service.EmailService;

import jakarta.mail.BodyPart;
import jakarta.mail.Folder;
import jakarta.mail.MessagingException;
import jakarta.mail.Multipart;
import jakarta.mail.Part;
import jakarta.mail.Session;
import jakarta.mail.Store;
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

	// Receiving all the emails
	
	@Value("${mail.store.protocol}")
	String  protocol;
	
	@Value("${mail.imaps.host}")
	String  host;
	
	@Value("${mail.imaps.port}")
	String  port;
	
	@Value("${spring.mail.username}")
	String  username;
	
	@Value("${spring.mail.password}")
	String  password;

	private String content;
	
	@Override
	public List<Message> getInboxMessages() throws IOException {
		Properties configurations = new Properties();
		
		configurations.setProperty("mail.store.protocol", protocol );
		configurations.setProperty("mail.imaps.host", host);
		configurations.setProperty("mail.imaps.port", port);
		
		Session session = Session.getDefaultInstance(configurations);
		
		try {
			Store store = session.getStore();
			
			store.connect(username , password);
			
			Folder inbox = store.getFolder("INBOX");
			inbox.open(Folder.READ_ONLY);
			
			jakarta.mail.Message[] messages = inbox.getMessages();
			
			List<Message> list = new ArrayList<>();
			
			for(jakarta.mail.Message message  : messages) {
				System.out.println(message.getSubject());
				System.out.println("----------------");
				
				String content = getContentFromEmailMessage(message);
				List<String> files = getFilesFromEmailMessage(message);
				list.add(Message.builder().subjects(message.getSubject()).content(content).files(files).build());
			}
			
			return list;
			
		} catch (MessagingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw new RuntimeException();
		}
		
	}

	private List<String> getFilesFromEmailMessage(jakarta.mail.Message message) throws MessagingException, IOException {
		List<String> files = new ArrayList<>();
		if(message.isMimeType("multipart/*")) {
			Multipart content = (Multipart) message.getContent();
			for(int i = 0; i<content.getCount(); i++) {
				BodyPart bodyPart = content.getBodyPart(i);
				if(Part.ATTACHMENT.equalsIgnoreCase(bodyPart.getDisposition())) {
					InputStream inputStream = bodyPart.getInputStream();
					File file = new File("src/main/resources/email"+bodyPart.getFileName());
					
					// saved file
					Files.copy(inputStream,file.toPath(),StandardCopyOption.REPLACE_EXISTING);
					
					// urls
					files.add(file.getAbsolutePath());
					
				}
			}
		}
		return files;
	}

	private String getContentFromEmailMessage(jakarta.mail.Message message) throws MessagingException, IOException {
		if(message.isMimeType("text/plain") || message.isMimeType("text/html")) {
			String content = (String) message.getContent();
			return content;
		} else if(message.isMimeType("multipart/*")) {
			Multipart part = (Multipart) message.getContent();
			for(int i = 0; i<part.getCount();i++) {
				BodyPart bodyPart = part.getBodyPart(i);
				if(bodyPart.isMimeType("text/plain")) {
					return (String) bodyPart.getContent();
				}
			}
		}
		return null;
	}

	
}
