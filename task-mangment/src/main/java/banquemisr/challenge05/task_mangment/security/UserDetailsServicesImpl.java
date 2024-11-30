package banquemisr.challenge05.task_mangment.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import banquemisr.challenge05.task_mangment.Domain.User;
import banquemisr.challenge05.task_mangment.Repo.UserRepo;


@Service
public class UserDetailsServicesImpl implements UserDetailsService{
	
	
	@Autowired
	private UserRepo userRepo;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        
        User user = userRepo.findByEmail(username).orElse(null);

        if(user == null) {
            throw new UsernameNotFoundException("No user with this user name");
        }else{
            System.out.println(user.getEmail());
        }

//        return org.springframework.security.core.userdetails.User.builder()
//            .username(user.getEmail())
//            .password(user.getPassword())
//            .build();
        return user;
    }

}
