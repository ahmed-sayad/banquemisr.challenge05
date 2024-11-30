package banquemisr.challenge05.task_mangment.Domain;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import jakarta.annotation.Generated;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Email;

@Entity
@Table(name = "users")
public class User implements UserDetails {

	
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Id
	@Column(name ="user_id")
	private Long id;
	
	@OneToMany(mappedBy = "user")
//	@JoinColumn(name = "user_fk", referencedColumnName = "user_id")
	private List<History> history = new ArrayList<>();
	
	
	@OneToMany(mappedBy = "user")
//	@JoinColumn(name = "user_fk", referencedColumnName = "user_id")
	private List<Task> task = new ArrayList<>();
	
	@OneToMany(mappedBy = "user")
//	@JoinColumn(name = "user_fk", referencedColumnName = "user_id")
	private List<Notification> notifications = new ArrayList<>();
	
	public List<Task> getTask() {
		return task;
	}

	public void setTask(List<Task> task) {
		this.task = task;
	}

	public List<Notification> getNotifications() {
		return notifications;
	}

	public void setNotifications(List<Notification> notifications) {
		this.notifications = notifications;
	}


	@Column(length = 1000)
	private String name;
	
	
	public List<History> getHistory() {
		return history;
	}

	public void setHistory(List<History> history) {
		this.history = history;
	}


	@Column(unique = true, nullable = false)
	@Email
	private String email;
	
	@Column(nullable = false)
	private String password;
	
	@Enumerated(EnumType.STRING)
	  private UserRole role;

//	  public User(String name, String password, UserRole role) {
//	    this.name = name;
//	    this.password = password;
//	    this.role = role;
//	  }
	
	 
	  public UserRole getRole() {
		return role;
	}

	public void setRole(UserRole role) {
		this.role = role;
	}

	public Collection<? extends GrantedAuthority> getAuthorities() {
	    if (this.role == UserRole.ADMIN) {
	      return List.of(new SimpleGrantedAuthority("ROLE_ADMIN"), new SimpleGrantedAuthority("ROLE_USER"));
	    }
	    return List.of(new SimpleGrantedAuthority("ROLE_USER"));
	  }
	  
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}


	@Override
	public String getUsername() {
		return this.email;
	}
	
	
	
	

}
