package banquemisr.challenge05.task_mangment.Domain.dto;

import banquemisr.challenge05.task_mangment.Domain.UserRole;

public class SignUpDto {
    String email;
    String password;
    UserRole role;
	
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public UserRole getRole() {
		return role;
	}
	public void setRole(UserRole role) {
		this.role = role;
	}
    
    
}
