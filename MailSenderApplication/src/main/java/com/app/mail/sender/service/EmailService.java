package com.app.mail.sender.service;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import com.app.mail.sender.emailmodel.Message;

public interface EmailService {

	// Send mail to single person
	public void sendEmail(String to , String subject , String message);
	
	// Send mail to multiple person
	public void sendEmail(String[] to , String subject , String message);
	
	// Send email with HTML content
	public void sendEmailWithHtml(String to , String subject , String htmlContent);
	
	// Send email with file
	public void sendEmailWithFile(String to , String subject , String message , File file);
	
	// Send email with inputStream
	public void sendEmailWithInputStreamFile(String to , String subject , String message , InputStream is);
	
	// Receive List of messages
	public List<Message> getInboxMessages() throws IOException;
	

	
}
