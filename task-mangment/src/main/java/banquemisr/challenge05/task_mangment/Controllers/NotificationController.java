package banquemisr.challenge05.task_mangment.Controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import banquemisr.challenge05.task_mangment.Domain.History;
import banquemisr.challenge05.task_mangment.Domain.Notification;
import banquemisr.challenge05.task_mangment.Domain.Task;
import banquemisr.challenge05.task_mangment.Domain.dto.NotifyDto;
import banquemisr.challenge05.task_mangment.Repo.NotificationRepo;
import banquemisr.challenge05.task_mangment.Repo.TaskRepo;
import banquemisr.challenge05.task_mangment.services.EmailServices;
import banquemisr.challenge05.task_mangment.services.NotificationServices;
import banquemisr.challenge05.task_mangment.services.TaskServices;
import banquemisr.challenge05.task_mangment.services.impl.NotificationServicesImpl;
import banquemisr.challenge05.task_mangment.services.impl.TaskServiceImpl;
import jakarta.validation.Valid;

@RestController
@RequestMapping("notifiy/")
public class NotificationController {

	@Autowired
    private NotificationRepo notifiyRepository;
	
	@Autowired
	 private EmailServices emailServices;
	
	
	@Autowired
	private NotificationServices notifiyService;
	
	

	public NotificationController(NotificationServices notificationServices) {
		super();
		this.notifiyService = notificationServices;
		}
	
	

	@GetMapping("all")
	public ResponseEntity<List<Notification>> getNotifies(){
		return ResponseEntity.ok(notifiyService.findAllNotifies());
	}
	
	@PostMapping("add")
	public ResponseEntity<String> addNotifi(@RequestBody @Valid NotifyDto notify){
		
		notifiyService.addNotifi(notify.getEmail(), notify.getMessage());
		return ResponseEntity.ok("Notification added successfully to this user");
	}
	
	@GetMapping("/search")
    public List<Notification> seaNotifications(
            @RequestParam(required = false) String user_id,
            @RequestParam(required = false) String message,
            @RequestParam(required = false) String time_stamp) {
        return ((NotificationServicesImpl) notifiyService).searchNotifications(user_id, message, time_stamp);
    }
	
	
	 @PostMapping("/send-email")
	    public String sendEmail(@RequestParam String to, 
	                            @RequestParam String subject, 
	                            @RequestParam String text) {
		 emailServices.sendEmail(to, subject, text);
	        return "Email sent successfully!";
	    }                                                               //Example: http://localhost:8080/notifiy/send-email?to=user@example.com&subject=Hi+User&text=This+is+a+test+email.
	
	 
	
}
