package banquemisr.challenge05.task_mangment.Domain.dto;

import banquemisr.challenge05.task_mangment.Domain.priorityEnum;
import jakarta.validation.constraints.NotBlank;


public class TaskPriorityDto {
	
	
	
	@NotBlank(message = "priority must not be nullable")
	private priorityEnum priority;

	public priorityEnum getPriority() {
		return priority;
	}

	public void setPriority(priorityEnum priority) {
		this.priority = priority;
	}
	
	
	
	

}
