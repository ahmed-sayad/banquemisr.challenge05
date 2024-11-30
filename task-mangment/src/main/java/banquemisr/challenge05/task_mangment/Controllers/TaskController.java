package banquemisr.challenge05.task_mangment.Controllers;

import java.awt.print.Pageable;
import java.util.List;
import java.util.Optional;

import org.hibernate.annotations.Filter;
import org.hibernate.query.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.data.web.SpringDataWebProperties.Sort;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import banquemisr.challenge05.task_mangment.Controllers.exceptions.ResourceNotFoundException;
import banquemisr.challenge05.task_mangment.Domain.Task;
import banquemisr.challenge05.task_mangment.Domain.dto.TaskPriorityDto;
import banquemisr.challenge05.task_mangment.Repo.TaskRepo;
import banquemisr.challenge05.task_mangment.services.TaskServices;
import banquemisr.challenge05.task_mangment.services.impl.TaskServiceImpl;
import jakarta.validation.Valid;


@RestController
@RequestMapping("tasks/")
public class TaskController {
	
	@Autowired
    private TaskRepo taskRepository;
	
	private TaskServices taskService;
	public TaskController(TaskServices taskService) {
		super();
		this.taskService = taskService;
	}

	@GetMapping("all")
	public ResponseEntity<List<Task>> getTasks(){
		return ResponseEntity.ok(taskService.findAllTasks());
	}
	
	
	@PostMapping("add")
	public ResponseEntity<String> addTask(@RequestBody @Valid Task new_t){
		taskService.addTask(new_t.getTitle(), new_t.getDescription(),new_t.getStatus(), new_t.getPriority(), new_t.getDue_date());
		return ResponseEntity.ok("Task added successfully");
	}
		
	@DeleteMapping("delete/{id}")
	public ResponseEntity<String> delete_task(@PathVariable Long id){
		taskService.delTask(id);
		return ResponseEntity.ok("Task with id " + id + " is deleted successfully");
	}
	
	  @GetMapping("/search")
	    public List<Task> searchEmployees(
	            @RequestParam(required = false) String title,
	            @RequestParam(required = false) String description,
	            @RequestParam(required = false) String status,
	            @RequestParam(required = false) String priority,
	            @RequestParam(required = false) String due_date) {
	        return ((TaskServiceImpl) taskService).searchTasks(title, description, status, priority, due_date);
	    }
	  
	
	@PutMapping("update/{id}")
    public ResponseEntity<Task> updateTask(@PathVariable Long id, @RequestBody @Valid Task updatedTask) {
		
        Task existingTask = taskRepository.findById(id).orElse(null);
        if (existingTask != null) {
            existingTask.setTitle(updatedTask.getTitle());
            existingTask.setDescription(updatedTask.getDescription());
            existingTask.setStatus(updatedTask.getStatus());
            existingTask.setPriority(updatedTask.getPriority());
            existingTask.setDue_date(updatedTask.getDue_date());       
            return ResponseEntity.ok(taskRepository.save(existingTask));
        }
        return null;
    }
	
	
	@PatchMapping("update_title/{id}")
    public  ResponseEntity<Task> updateTask_title(@PathVariable Long id, @RequestBody Task updatedTaskTit) {
		
        Task existingTask = taskRepository.findById(id).orElse(null);
        if (existingTask != null) {
            existingTask.setTitle(updatedTaskTit.getTitle());
            return ResponseEntity.ok(taskRepository.save(existingTask));
        }
        return null;
    }
	
	@PatchMapping("update_description/{id}")
    public Task updateTask_description(@PathVariable Long id, @RequestBody Task updatedTaskDesc) {
		
        Task existingTask = taskRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("No such task with id " + id));
        	existingTask.setDescription(updatedTaskDesc.getDescription());
        	return taskRepository.save(existingTask);
    }
	
	@PatchMapping("update_status/{id}")
    public  ResponseEntity<Task> updateTask_status(@PathVariable Long id, @RequestBody Task updatedTaskStat) {
		
        Task existingTask = taskRepository.findById(id).orElse(null);
        if (existingTask != null) {
        	existingTask.setStatus(updatedTaskStat.getStatus());
        	return ResponseEntity.ok(taskRepository.save(existingTask));
        }
        return null;
    }
	
	@PatchMapping("update_priority/{id}")
    public  ResponseEntity<Task> updateTask_priority(@PathVariable Long id, @RequestBody @Valid TaskPriorityDto updatedTaskPrio) {
		
		
        Task existingTask = taskRepository.findById(id).orElse(null);
        if (existingTask != null) {
        	existingTask.setPriority(updatedTaskPrio.getPriority());
        	return ResponseEntity.ok(taskRepository.save(existingTask));
        }
        return null;
    }
	
	@PutMapping("update_dueAt/{id}")
    public  ResponseEntity<Task> updateTask_dueDate(@PathVariable Long id, @RequestBody Task updatedTaskDueAt) {
		
        Task existingTask = taskRepository.findById(id).orElse(null);
        if (existingTask != null) {
        	existingTask.setDue_date(updatedTaskDueAt.getDue_date());
        	return ResponseEntity.ok(taskRepository.save(existingTask));
        }
        return null;
    }
	
	
	
	@GetMapping
    public ResponseEntity<Page> gatTasks(
            @RequestParam(defaultValue = "0") int pageNo,
            @RequestParam(defaultValue = "10") int pageSize) {
        
        Page products = taskService.get(pageNo, pageSize);
        return ResponseEntity.ok(products);
    }
	

}
