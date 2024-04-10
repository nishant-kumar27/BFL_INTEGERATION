package com.bfl.ui.auth.dto;

import javax.validation.constraints.NotEmpty;

import com.bfl.ui.constraints.FieldMatch;

@FieldMatch.List({
    @FieldMatch(first = "newPassword", second = "confirmNewPassword", message = "Confirm password does not match with password"),
})
public class ChangePasswordDto {


    @NotEmpty
    private String oldPassword;
    @NotEmpty
    private String newPassword;
    @NotEmpty
    private String confirmNewPassword;
	public String getOldPassword() {
		return oldPassword;
	}
	public void setOldPassword(String oldPassword) {
		this.oldPassword = oldPassword;
	}
	public String getNewPassword() {
		return newPassword;
	}
	public void setNewPassword(String newPassword) {
		this.newPassword = newPassword;
	}
	public String getConfirmNewPassword() {
		return confirmNewPassword;
	}
	public void setConfirmNewPassword(String confirmNewPassword) {
		this.confirmNewPassword = confirmNewPassword;
	}
}
