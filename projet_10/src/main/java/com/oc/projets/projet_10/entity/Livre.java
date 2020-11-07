package com.oc.projets.projet_10.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@Table(name = "livre")
@EntityListeners(AuditingEntityListener.class)
@JsonIgnoreProperties(value = {"createdAt", "updateAt"},
		allowGetters = true)
public class Livre {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	private String titre;
	
	@ManyToOne
	@JoinColumn(name = "auteur_id", nullable = false)
	private Auteur auteur;
	
	private String genre;
	
	@Column(name = "nbre_exemplaires")
	private int nbreExemplaires;

	@Column(name = "nbre_exemplaires_total")
	private int nbreExemplairesTotal;
	
//	@Column(name = "full_name_auteur")
//	private String fullNameAuteur;
	
	public Livre() {
		// TODO Auto-generated constructor stub
		super();
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getTitre() {
		return titre;
	}

	public void setTitre(String titre) {
		this.titre = titre;
	}

	public Auteur getAuteur() {
		return auteur;
	}

	public void setAuteur(Auteur auteur) {
		this.auteur = auteur;
	}

	public String getGenre() {
		return genre;
	}

	public void setGenre(String genre) {
		this.genre = genre;
	}

	public int getNbreExemplaires() {
		return nbreExemplaires;
	}


	public int getNbreExemplairesTotal() {
		return this.nbreExemplairesTotal;
	}

	public void setNbreExemplaires(int nbreExemplaires) {
		this.nbreExemplaires = nbreExemplaires;
	}

	public void setNbreExemplairesTotal(int nbreExemplairesTotal) {
		this.nbreExemplairesTotal = nbreExemplairesTotal;
	}

//	public String getFullNameAuteur() {
//		return fullNameAuteur;
//	}
//
//	public void setFullNameAuteur(String fullNameAuteur) {
//		this.fullNameAuteur = fullNameAuteur;
//	}

	@Override
	public String toString() {
		return "Livre [id=" + id + ", titre=" + titre + ", auteur=" + auteur + ", genre=" + genre + ", nbreExemplaires="
				+ nbreExemplaires + "]";
	}
}
