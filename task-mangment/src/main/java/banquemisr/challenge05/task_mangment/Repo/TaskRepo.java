package banquemisr.challenge05.task_mangment.Repo;

import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import banquemisr.challenge05.task_mangment.Domain.Task;

@Repository
public interface TaskRepo extends CrudRepository<Task, Long>, JpaSpecificationExecutor<Task> {

	

}
