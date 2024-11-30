package banquemisr.challenge05.task_mangment.Repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import banquemisr.challenge05.task_mangment.Domain.History;

@Repository
public interface HistoryRepo extends JpaRepository<History, Long>,  JpaSpecificationExecutor<History>{
	 

}
