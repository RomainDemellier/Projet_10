package com.oc.projets.projet_10.dto;

public class UsagerLoginDTO {

	private String email;
	
	private String password;

	public UsagerLoginDTO() {
		super();
		// TODO Auto-generated constructor stub
	}

	public UsagerLoginDTO(String email, String password) {
		super();
		this.email = email;
		this.password = password;
	}

	public String getEmail() {
		return email;
	}

	public String getPassword() {
		return password;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	@Override
	public String toString() {
		return "UsagerLoginDTO [email=" + email + "]";
	}
}
