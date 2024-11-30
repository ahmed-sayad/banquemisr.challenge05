package banquemisr.challenge05.task_mangment.services;

import org.springframework.stereotype.Service;

import banquemisr.challenge05.task_mangment.Domain.User;
import banquemisr.challenge05.task_mangment.Domain.dto.SignUpDto;

@Service
public interface UserService {
	User findByEmail(String email);
	
	String login(String email, String password);

	 
	User signup(SignUpDto signuupDto);
}
