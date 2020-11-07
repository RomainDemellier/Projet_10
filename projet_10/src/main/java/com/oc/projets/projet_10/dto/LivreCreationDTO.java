package com.oc.projets.projet_10.dto;

import com.oc.projets.projet_10.entity.Auteur;

public class LivreCreationDTO {
	
	private String titre;
	
	private AuteurDTO auteur;
	
	private String genre;
	
	private int nbreExemplaires;
	
	private String fullNameAuteur;

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

	public String getFullNameAuteur() {
		return fullNameAuteur;
	}

	public void setFullNameAuteur(String fullNameAuteur) {
		this.fullNameAuteur = fullNameAuteur;
	}

	@Override
	public String toString() {
		return "LivreCreationDTO [titre=" + titre + ", auteur=" + auteur + ", genre=" + genre + ", nbreExemplaires="
				+ nbreExemplaires + ", fullNameAuteur=" + fullNameAuteur + "]";
	}
}
