package rs.ac.singidunum.server.entities;

import java.util.Arrays;
import java.util.List;

import org.springframework.data.annotation.Id;

public class User {
	
	@Id
	public String id;
	
	private String firstName;
	private String lastName;
	private String username;
	private String password;
	private List<String> roles;
	
	public User() {
		
	}
	
	public User(String firstName, String lastName, String password, String[] roles) {
		this.firstName = firstName;
		this.lastName = lastName;
		this.username = firstName.toLowerCase() + lastName.toLowerCase();
		this.password = password;
		this.roles = Arrays.asList(roles);
	}
	
	@Override
	public String toString() {
	    return String.format(
	        "Customer[id=%s, firstName='%s', lastName='%s']",
	        id, firstName, lastName);
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
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

	public List<String> getRoles() {
		return roles;
	}

	public void setRoles(List<String> role) {
		this.roles = role;
	}
	
}
