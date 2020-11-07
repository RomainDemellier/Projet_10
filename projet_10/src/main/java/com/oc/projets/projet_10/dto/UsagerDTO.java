package com.oc.projets.projet_10.dto;

import java.io.Serializable;

public class UsagerDTO implements Serializable {

	private String nom;
	
	private String prenom;
	
	private String email;
	
	private String password;
	
	private String confirmPassword;

	
	
	public UsagerDTO() {
	}

	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public String getPrenom() {
		return prenom;
	}

	public void setPrenom(String prenom) {
		this.prenom = prenom;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getConfirmPassword() {
		return confirmPassword;
	}

	public void setConfirmPassword(String confirmPassword) {
		this.confirmPassword = confirmPassword;
	}

	@Override
	public String toString() {
		return "UsagerDTO [nom=" + nom + ", prenom=" + prenom + ", email=" + email + "]";
	}
}
