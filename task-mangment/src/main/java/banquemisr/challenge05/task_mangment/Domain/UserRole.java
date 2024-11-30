package banquemisr.challenge05.task_mangment.Domain;

public enum UserRole {
	
	  ADMIN("admin"),
	  USER("user");

	  private String role;

	  UserRole(String role) {
	    this.role = role;
	  }

	  public String getValue() {
	    return role;
	  }

}
