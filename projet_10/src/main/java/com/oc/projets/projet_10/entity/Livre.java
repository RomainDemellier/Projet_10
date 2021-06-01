package com.oc.projets.projet_10.entity;

import javax.persistence.*;

import org.springframework.boot.context.properties.bind.DefaultValue;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

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

	private int nbreTotal;

	@Column(columnDefinition = "integer default 0")
	private int nbreReservations;
	
	public Livre() {
		// TODO Auto-generated constructor stub
		super();
	}

	public Livre(Long id, String titre, Auteur auteur, String genre, int nbreExemplaires, int nbreTotal, Boolean reservable) {
		this.id = id;
		this.titre = titre;
		this.auteur = auteur;
		this.genre = genre;
		this.nbreExemplaires = nbreExemplaires;
		this.nbreTotal = nbreTotal;
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

	public void setNbreExemplaires(int nbreExemplaires) {
		this.nbreExemplaires = nbreExemplaires;
	}


	public int getNbreTotal() {
		return this.nbreTotal;
	}

	public void setNbreTotal(int nbreTotal) {
		this.nbreTotal = nbreTotal;
	}

	public int getNbreReservations() {
		return nbreReservations;
	}

	public void setNbreReservations(int nbreReservations) {
		this.nbreReservations = nbreReservations;
	}

	@Override
	public String toString() {
		return "Livre{" +
				"id=" + id +
				", titre='" + titre + '\'' +
				", auteur=" + auteur +
				", genre='" + genre + '\'' +
				", nbreExemplaires=" + nbreExemplaires +
				", nbreTotal=" + nbreTotal +
				", nbreReservations=" + nbreReservations +
//				", datePlusProcheRetourPrevue=" + datePlusProcheRetourPrevue +
				'}';
	}
}
