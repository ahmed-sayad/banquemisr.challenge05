package banquemisr.challenge05.task_mangment.services;

import java.awt.print.Pageable;
import java.util.List;

import org.hibernate.query.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import banquemisr.challenge05.task_mangment.Domain.Task;
import banquemisr.challenge05.task_mangment.Domain.priorityEnum;
import banquemisr.challenge05.task_mangment.Domain.statusEnum;
import banquemisr.challenge05.task_mangment.Repo.TaskRepo;

@Service
public interface TaskServices {
		
	List<Task> findAllTasks();
	
	void addTask(String title, String description, statusEnum status, priorityEnum priority, String due_date);
	
	void delTask(Long id);
	
	void updateTask_title(Long id, String title);
	
	void updateTask_description(Long id, String description);
	
	void updateTask_status(Long id, String status);
		
	void updateTask_priority(Long id, String priority);
	
	void updateTask_dueDate(Long id, String due_date);
	
	void updateTask(String title, String description, String status, String priority, String due_date);	
	
	
}
