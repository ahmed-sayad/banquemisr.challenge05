package banquemisr.challenge05.task_mangment.services;

import java.util.List;

import org.springframework.stereotype.Service;

import banquemisr.challenge05.task_mangment.Domain.Notification;

@Service
public interface NotificationServices {
	
	
	List<Notification> findAllNotifies();

	void addNotifi(String email, String message);
	
}
