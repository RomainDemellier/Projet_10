package com.oc.projets.projet_10.dto;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import com.oc.projets.projet_10.entity.Emprunt;

public class UsagerGetDTO implements Serializable {

	private Long id;
	
	private String nom;
	
	private String prenom;
	
	private String email;
	
	private String role;

	public UsagerGetDTO() {
	}
	
	

	public UsagerGetDTO(Long id, String nom, String prenom, String email) {
	super();
	this.id = id;
	this.nom = nom;
	this.prenom = prenom;
	this.email = email;
}



	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
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

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}



	@Override
	public String toString() {
		return "UsagerGetDTO [id=" + id + ", nom=" + nom + ", prenom=" + prenom + ", email=" + email + ", role=" + role
				+ "]";
	}	
}
