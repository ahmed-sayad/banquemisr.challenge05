package data.specification;

import org.springframework.data.jpa.domain.Specification;

import banquemisr.challenge05.task_mangment.Domain.Notification;


public class NotificationSpecification {
	
	 public static Specification<Notification> hasUserID(String user_id) {
	        return (root, query, criteriaBuilder) -> 
	        user_id == null ? null : criteriaBuilder.equal(root.get("user_id"), user_id);
	    }
	 
	 public static Specification<Notification> hasMessage(String message) {
	        return (root, query, criteriaBuilder) -> 
	        message == null ? null : criteriaBuilder.equal(root.get("message"), message);
	    }
	 
	 public static Specification<Notification> hasTimestamp(String time_stamp) {
	        return (root, query, criteriaBuilder) -> 
	        time_stamp == null ? null : criteriaBuilder.equal(root.get("timestamp"), time_stamp);
	    }

}
