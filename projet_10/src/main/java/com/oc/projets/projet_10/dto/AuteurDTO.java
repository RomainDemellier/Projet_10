package com.oc.projets.projet_10.dto;

public class AuteurDTO {

	private Long id;

	private String nom;
	
	private String prenom;
	
	public AuteurDTO() {
		
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

	@Override
	public String toString() {
		return "AuteurDTO [id=" + id + ", nom=" + nom + ", prenom=" + prenom + "]";
	}
	
}
