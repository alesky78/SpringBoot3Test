package it.spaghettisource.springbootexample.service.dto;

public class UserRequest {

	private String username;
	private String password;	
	private String role;
	
	public UserRequest() {
		super();
	}

	public UserRequest(String userName, String password, String role) {
		super();
		this.username = userName;
		this.password = password;
		this.role = role;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String userName) {
		this.username = userName;
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
		return "UserRequest [userName=" + username + ", password=" + password + ", role=" + role + "]";
	}

}
