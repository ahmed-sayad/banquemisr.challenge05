package banquemisr.challenge05.task_mangment.Domain;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.hibernate.Length;
import org.springframework.data.jpa.repository.JpaRepository;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;

@Entity
@Table(name = "tasks")
public class Task {
		
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Id
    @Column(name = "task_id")
	private Long id;
	
	@Column(nullable = false)
	@NotBlank(message = "Task title cannot be blank")
	private String  title;
	
	@Column(length = Length.LONG32)
	private String description;
	
	@Enumerated(EnumType.STRING)
	@Column
	private statusEnum status;
	
	@Enumerated(EnumType.STRING)
	@Column
	private priorityEnum priority; 	
	
	
	@ManyToOne()
    @JoinColumn(name = "user_id")
    private User user;
	
	
	@OneToMany(mappedBy = "task")
//	@JoinColumn(name="task_fk", referencedColumnName = "task_id")
	private List<History> histories = new ArrayList<>();
	
	
	@OneToMany(mappedBy = "task")
//	@JoinColumn(name="task_fk", referencedColumnName = "task_id")
	private List<Notification> notifications = new ArrayList<>();
	
	
	public List<Notification> getNotifications() {
		return notifications;
	}
	public void setNotifications(List<Notification> notifications) {
		this.notifications = notifications;
	}

	
	  
	
	public List<History> getHistories() {
		return histories;
	}
	public void setHistories(List<History> histories) {
		this.histories = histories;
	}
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}

	@Column(length = Length.LONG32)
	private String due_date;
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	
	public statusEnum getStatus() {
		return status;
	}
	public void setStatus(statusEnum status) {
		this.status = status;
	}
	public priorityEnum getPriority() {
		return priority;
	}
	public void setPriority(priorityEnum priority) {
		this.priority = priority;
	}
	public void setDue_date(String due_date) {
		this.due_date = due_date;
	}
	public String getDue_date() {
		return due_date;
	}
	@Override
	public String toString() {
		return "Task [id=" + id + ", title=" + title + ", description=" + description + "]";
	}
		
	public interface TaskRepo extends JpaRepository<Task, Long> {}


}

