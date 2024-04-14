package com.ccifuentes.equifax.security.models.dto;

public class MessageDTO {
	
    private String message;

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public MessageDTO(String message) {
		super();
		this.message = message;
	}
    
    

}
