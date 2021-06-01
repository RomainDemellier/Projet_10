package com.oc.projets.projet_10.dto;

import java.time.LocalDate;

public class LivreDTO {

	private Long id;
	
	private String titre;
	
	private AuteurDTO auteur;
	
	private String genre;
	
	private int nbreExemplaires;

	private int nbreTotal;

	private int nbreReservations;

	private LocalDate datePlusProcheRetourPrevue;
	
	public LivreDTO() {
		
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

	public AuteurDTO getAuteur() {
		return auteur;
	}

	public void setAuteur(AuteurDTO auteur) {
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
		return nbreTotal;
	}

	public void setNbreTotal(int nbreTotal) {
		this.nbreTotal = nbreTotal;
	}

	public LocalDate getDatePlusProcheRetourPrevue() {
		return datePlusProcheRetourPrevue;
	}

	public void setDatePlusProcheRetourPrevue(LocalDate datePlusProcheRetourPrevue) {
		this.datePlusProcheRetourPrevue = datePlusProcheRetourPrevue;
	}

	public int getNbreReservations() {
		return nbreReservations;
	}

	public void setNbreReservations(int nbreReservations) {
		this.nbreReservations = nbreReservations;
	}

	@Override
	public String toString() {
		return "LivreDTO{" +
				"id=" + id +
				", titre='" + titre + '\'' +
				", auteur=" + auteur +
				", genre='" + genre + '\'' +
				", nbreExemplaires=" + nbreExemplaires +
				", nbreReservations=" + nbreReservations +
				", datePlusProcheRetourPrevue=" + datePlusProcheRetourPrevue +
				'}';
	}
}
