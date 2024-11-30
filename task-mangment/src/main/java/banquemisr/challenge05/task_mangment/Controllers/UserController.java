
package banquemisr.challenge05.task_mangment.Controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import banquemisr.challenge05.task_mangment.Domain.User;
import banquemisr.challenge05.task_mangment.Domain.dto.LoginRequest;
import banquemisr.challenge05.task_mangment.Domain.dto.SignUpDto;
import banquemisr.challenge05.task_mangment.Repo.UserRepo;
import banquemisr.challenge05.task_mangment.security.JwtUtills;
import banquemisr.challenge05.task_mangment.services.UserService;
import jakarta.validation.Valid;


@RestController
@RequestMapping("user/")
public class UserController {
	
	@Autowired
	AuthenticationManager authMan;
	
	
	@Autowired
	JwtUtills utills;
	
	@Autowired
	UserRepo userRepo;
	
	
	@Autowired
	UserService userService;
	
	
	@Autowired
	PasswordEncoder passwordEncoder;

    @PostMapping("login")
    public ResponseEntity<?> login(@RequestBody LoginRequest loginRequest){
        String jwt = userService.login(loginRequest.getEmail(),loginRequest.getPassword());
        return ResponseEntity.ok(jwt);

    }
    
    
    @PostMapping("signup")
    public ResponseEntity<?> signup(@RequestBody @Valid SignUpDto signup){        
        User user = userService.signup(signup);
        return ResponseEntity.ok(user);

    }
    
    
    
    
    @GetMapping("all")
    public ResponseEntity<?> allUsers(){
    	List<User> users = userRepo.findAll();
    	return ResponseEntity.ok(users);
    	
    }
    
}
