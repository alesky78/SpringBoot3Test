package it.spaghettisource.springbootexample.service.dto;

public class UserResponse {

	private Long id;
	private String username;
	private String password;	
	private String role;
	
	public UserResponse() {
		super();
	}

	public UserResponse(Long id, String userName, String password, String role) {
		super();
		this.id = id;
		this.username = userName;
		this.password = password;
		this.role = role;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	@Override
	public String toString() {
		return "UserResponse [id=" + id + ", userName=" + username + ", password=" + password + ", role=" + role + "]";
	}

}
