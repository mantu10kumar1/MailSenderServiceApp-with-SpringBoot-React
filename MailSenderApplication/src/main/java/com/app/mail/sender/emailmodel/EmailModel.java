package com.app.mail.sender.emailmodel;



import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class EmailModel {
  
	
  private String to;
  private String subject;
  private String message;
  
  

}
