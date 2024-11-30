package banquemisr.challenge05.task_mangment.Domain.dto;

import org.hibernate.validator.constraints.Length;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public class NotifyDto {
	
	@Email
	@NotBlank
	String email;
	
	@NotBlank
	@Length(min = 10, max=org.hibernate.Length.LONG32)
	String message;

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}
	
	
	
	
	

}
