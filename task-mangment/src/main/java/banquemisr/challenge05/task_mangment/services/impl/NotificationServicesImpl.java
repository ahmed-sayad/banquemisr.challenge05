package banquemisr.challenge05.task_mangment.services.impl;

import java.util.Date;
import java.util.List;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;

import banquemisr.challenge05.task_mangment.Controllers.exceptions.ResourceNotFoundException;
import banquemisr.challenge05.task_mangment.Domain.History;
import banquemisr.challenge05.task_mangment.Domain.Notification;
import banquemisr.challenge05.task_mangment.Domain.Task;
import banquemisr.challenge05.task_mangment.Domain.User;
import banquemisr.challenge05.task_mangment.Repo.HistoryRepo;
import banquemisr.challenge05.task_mangment.Repo.NotificationRepo;
import banquemisr.challenge05.task_mangment.Repo.UserRepo;
import banquemisr.challenge05.task_mangment.services.NotificationServices;
import data.specification.NotificationSpecification;
import data.specification.TaskSpecification;

@Component
public class NotificationServicesImpl implements NotificationServices {
	
	private final int PAGE_SIZE=5;

	private NotificationRepo notificationRepo;
	private UserRepo userRepo;
	

	public NotificationServicesImpl(NotificationRepo notificationRepo) {
		super();
		this.notificationRepo = notificationRepo;
	
	}

	@Override
	public List<Notification> findAllNotifies() {
		return notificationRepo.findAll();
	}

	@Override
	public void addNotifi(String email, String message) {
		
		
		User user =  userRepo.findByEmail(email).orElseThrow(() -> new ResourceNotFoundException("There is no user with id " + email));
		Notification newNotifi = new Notification();
		newNotifi.setUser(user);
		newNotifi.setMessage(message);
		newNotifi.setTime_stamp(this.get_current_time());
		System.out.println(newNotifi);
		notificationRepo.save(newNotifi);
		
	}
	
	
	
	private Date get_current_time() {
		return new Date();
	}
	
	@SuppressWarnings("unchecked")
	public List<Notification> searchNotifications(String user_id, String message, int page_num) {
        Specification<Notification> spec = Specification.where(NotificationSpecification.hasUserID(user_id))
                .and(NotificationSpecification.hasMessage(message));
        Pageable page = PageRequest.of(PAGE_SIZE,page_num );
        return (List<Notification>) notificationRepo.findAll(page);  
        }

	

}