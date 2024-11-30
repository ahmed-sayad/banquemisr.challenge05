package banquemisr.challenge05.task_mangment.services.impl;

import java.time.Period;
import java.util.List;

import org.hibernate.Length;
import org.hibernate.query.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.crossstore.ChangeSetPersister.NotFoundException;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Component;

import banquemisr.challenge05.task_mangment.Domain.Task;
import banquemisr.challenge05.task_mangment.Domain.User;
import banquemisr.challenge05.task_mangment.Domain.actionEnum;
import banquemisr.challenge05.task_mangment.Domain.priorityEnum;
import banquemisr.challenge05.task_mangment.Domain.statusEnum;
import banquemisr.challenge05.task_mangment.Repo.TaskRepo;
import banquemisr.challenge05.task_mangment.services.HistoryServices;
import banquemisr.challenge05.task_mangment.services.TaskServices;
import banquemisr.challenge05.task_mangment.services.UserService;
import data.specification.TaskSpecification;
import jakarta.persistence.Column;


@Component
public class TaskServiceImpl implements TaskServices{
	
	@Autowired
	private final TaskRepo taskRepo;
	
	private final HistoryServices historyServe;
	
	
	private final UserService userService;

	
	public TaskServiceImpl(TaskRepo taskRepo, HistoryServices historyServe, UserService userService) {
		this.taskRepo = taskRepo;
		this.historyServe = historyServe;
		this.userService = userService;
	}
	

	@Override
	public List<Task> findAllTasks() {
		
		return (List<Task>) taskRepo.findAll();
		
	}

	@Override
	public void addTask(String title, String description, statusEnum status, priorityEnum priority, String due_date) {
		Task newTask = new Task();
		newTask.setTitle(title);
		newTask.setDescription(description);
		newTask.setStatus(status);
		newTask.setPriority(priority);
		newTask.setDue_date(due_date);
		System.out.println(newTask);
		taskRepo.save(newTask);
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		String email = (String) auth.getPrincipal();
		User user = userService.findByEmail(email);
		historyServe.addHistory(newTask, actionEnum.ADDED, user);
	}

	@Override
	public void delTask(Long id) {
	   taskRepo.findById(id).orElseThrow();
	   taskRepo.deleteById(id);
		
	}
	
	public List<Task> searchTasks(String title, String description, String status, String priority, String due_date) {
        Specification<Task> spec = Specification.where(TaskSpecification.hasTitle(title))
                .and(TaskSpecification.hasDescription(description))
                .and(TaskSpecification.hasStatus(status))
                .and(TaskSpecification.hasPriority(priority))
                .and(TaskSpecification.hasDueDate(due_date));
        

        return taskRepo.findAll(spec);
    }
	


	@Override
	public void updateTask_title(Long id, String title) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void updateTask_description(Long id, String description) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void updateTask_status(Long id, String status) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void updateTask_priority(Long id, String description) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void updateTask_dueDate(Long id, String due_date) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void updateTask(String title, String description, String status, String priority, String due_date) {
		// TODO Auto-generated method stub
		
	}
	
        

	
	
}
