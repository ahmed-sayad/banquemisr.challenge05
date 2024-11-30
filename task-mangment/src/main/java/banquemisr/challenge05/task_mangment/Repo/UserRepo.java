package banquemisr.challenge05.task_mangment.Repo;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import banquemisr.challenge05.task_mangment.Domain.User;

@Repository
public interface UserRepo extends JpaRepository<User, Long>{
	
	Optional<User> findByEmail(String email);

}
