package com.oc.projets.projet_10.dto;

import java.io.Serializable;

public class LivreEmpruntDTO implements Serializable {

	private Long id;
	
	private String titre;
	
	private int nbreExemplaires;

	public LivreEmpruntDTO() {
		super();
		// TODO Auto-generated constructor stub
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

	public int getNbreExemplaires() {
		return nbreExemplaires;
	}

	public void setNbreExemplaires(int nbreExemplaires) {
		this.nbreExemplaires = nbreExemplaires;
	}

	@Override
	public String toString() {
		return "LivreEmpruntDTO [id=" + id + ", titre=" + titre + ", nbreExemplaires=" + nbreExemplaires + "]";
	}
}
