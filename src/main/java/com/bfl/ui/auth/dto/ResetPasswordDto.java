package com.bfl.ui.auth.dto;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;


public class ResetPasswordDto {


    @NotEmpty
    @Email
    private String username;

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}
   
	
}
