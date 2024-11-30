package banquemisr.challenge05.task_mangment.services.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import banquemisr.challenge05.task_mangment.Controllers.exceptions.ResourceNotFoundException;
import banquemisr.challenge05.task_mangment.Domain.User;
import banquemisr.challenge05.task_mangment.Domain.dto.SignUpDto;
import banquemisr.challenge05.task_mangment.Repo.UserRepo;
import banquemisr.challenge05.task_mangment.security.JwtUtills;
import banquemisr.challenge05.task_mangment.services.UserService;

@Component
public class UserServiceImpl implements UserService{
	
	
	private final UserRepo userRepo;
	
	
	private final AuthenticationManager authMan;
	
	
	private final JwtUtills utills;
	
	
	private final PasswordEncoder passwordEncoder;
	
	
	public UserServiceImpl(UserRepo userRepo, AuthenticationManager authMan, JwtUtills utills, PasswordEncoder passwordEncoder) {
		this.userRepo = userRepo;
		this.utills = utills;
		this.authMan = authMan;
		this.passwordEncoder = passwordEncoder;
	}



	@Override
	public User findByEmail(String email) {
		// TODO Auto-generated method stub
		
		
		User user = userRepo.findByEmail(email).orElseThrow(()->new ResourceNotFoundException("There no user for this mail"));
		return user;
	}



	@Override
	public String login(String email, String password) {
		// TODO Auto-generated method stub
		Authentication authentication=authMan.authenticate(new UsernamePasswordAuthenticationToken(email,password));
        SecurityContextHolder.getContext().setAuthentication(authentication);
        String jwt= utills.generateJwtToken(authentication);
		return jwt;
	}



	@Override
	public User signup(SignUpDto signup) {
		// TODO Auto-generated method stub
		User user = new User();
        user.setPassword(passwordEncoder.encode(signup.getPassword()));
        user.setEmail(signup.getEmail());
        user.setName(signup.getEmail());
        user.setRole(signup.getRole());
        userRepo.save(user);
        return user;
	}

}
