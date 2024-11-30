package banquemisr.challenge05.task_mangment.Repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import banquemisr.challenge05.task_mangment.Domain.Notification;

public interface NotificationRepo extends JpaRepository<Notification, Long>,  JpaSpecificationExecutor<Notification>{

}
