package banquemisr.challenge05.task_mangment.Domain;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "history")
public class History  {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name= "history_id")
	private Long id;
	
	
    @ManyToOne()
    @JoinColumn(name = "user_id")
    private User user;
    
    @ManyToOne()
    @JoinColumn(name = "task_id")
    private Task task;
    
    
	@Column
	private actionEnum action;
	
	      // which user or admin put this action to task
    
    public Task getTask() {
		return task;
	}

	public void setTask(Task task) {
		this.task = task;
	}


	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}


	public actionEnum getAction() {
		return action;
	}
	
	
	public void setAction(actionEnum action) {
		this.action = action;
	}






}
