//package com.app.mail.sender.controller;
//
//import java.io.IOException;
//import java.util.Date;
//
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.CrossOrigin;
//import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.bind.annotation.RequestBody;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RequestPart;
//import org.springframework.web.bind.annotation.RestController;
//import org.springframework.web.multipart.MultipartFile;
//
//import com.app.mail.sender.emailmodel.EmailModel;
//import com.app.mail.sender.service.EmailService;
//
//@RestController
//@RequestMapping("/api/v1/email")
//@CrossOrigin("*")
//public class EmailController {
//
//	private EmailService emailService;
////	private EmailRepository emailRepo;
//	
//	public EmailController(EmailService emailService) {
//		super();
//		this.emailService = emailService;
//	}
//
//	@PostMapping("/send")
//	public ResponseEntity<String> sendEmail(@RequestBody EmailModel request){
//		emailService.sendEmailWithHtml(request.getTo(), request.getSubject(), request.getMessage());
//		return ResponseEntity.ok("Email send successfully...\n httpsStatus Ok \n success true");
//	}
//	
//	@PostMapping("/send-with-file")
//	public ResponseEntity<String> sendEmailWithFile(@RequestPart EmailModel request ,@RequestPart MultipartFile file) throws IOException{
//		emailService.sendEmailWithInputStreamFile(request.getTo(), request.getSubject(), request.getMessage(), file.getInputStream());
////		emailRepo.save(request);
//		return ResponseEntity.ok("Email send successfully...\nhttpsStatus Ok\nsuccess true");
//	}
//}
