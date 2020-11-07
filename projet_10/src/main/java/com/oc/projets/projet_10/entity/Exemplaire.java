package com.oc.projets.projet_10.entity;

import java.io.Serializable;

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
@Table(name = "exemplaire")
@EntityListeners(AuditingEntityListener.class)
@JsonIgnoreProperties(value = {"createdAt", "updateAt"},
		allowGetters = true)
public class Exemplaire implements Serializable {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@ManyToOne
	@JoinColumn(name = "livre_id", nullable = false)
	private Livre livre;
	
	private String etat;
	
//	private String titre;
//	
//	@ManyToOne
//	@JoinColumn(name = "auteur_id", nullable = false)
//	private Auteur auteur;
//	
//	private String genre;
//	
//	@Column(name = "nbre_exemplaires")
//	private int nbreExemplaires;
//	
//	@Column(name = "full_name_auteur")
//	private String fullNameAuteur;

	public Exemplaire() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Livre getLivre() {
		return livre;
	}

	public void setLivre(Livre livre) {
		this.livre = livre;
	}

	public String getEtat() {
		return etat;
	}

	public void setEtat(String etat) {
		this.etat = etat;
	}

	@Override
	public String toString() {
		return "Exemplaire [id=" + id + ", livre=" + livre + ", etat=" + etat + "]";
	}

//	public String getTitre() {
//		return titre;
//	}
//
//	public void setTitre(String titre) {
//		this.titre = titre;
//	}
//
//	public Auteur getAuteur() {
//		return auteur;
//	}
//
//	public void setAuteur(Auteur auteur) {
//		this.auteur = auteur;
//	}
//
//	public String getGenre() {
//		return genre;
//	}
//
//	public void setGenre(String genre) {
//		this.genre = genre;
//	}
//
//	public int getNbreExemplaires() {
//		return nbreExemplaires;
//	}
//
//	public void setNbreExemplaires(int nbreExemplaires) {
//		this.nbreExemplaires = nbreExemplaires;
//	}
//
//	public String getFullNameAuteur() {
//		return fullNameAuteur;
//	}
//
//	public void setFullNameAuteur(String fullNameAuteur) {
//		this.fullNameAuteur = fullNameAuteur;
//	}
//
//	@Override
//	public String toString() {
//		return "Livre [id=" + id + ", titre=" + titre + ", auteur=" + auteur + ", genre=" + genre + ", nbreExemplaires="
//				+ nbreExemplaires + ", fullNameAuteur=" + fullNameAuteur + "]";
//	}
}
