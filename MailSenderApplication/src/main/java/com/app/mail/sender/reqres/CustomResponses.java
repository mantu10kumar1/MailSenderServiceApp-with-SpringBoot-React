package com.app.mail.sender.reqres;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

import org.springframework.http.HttpStatus;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@Builder
public class CustomResponses {
	
	private String message;
	private HttpStatus httpStatus;
	private boolean success;
	
}
