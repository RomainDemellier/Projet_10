package com.oc.projets.projet_10.dto;

public class ExemplaireDTO {
	
	private Long id;
	
	private String etat;
	
	private LivreDTO livre;
	
	public ExemplaireDTO() {
		// TODO Auto-generated constructor stub
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public LivreDTO getLivre() {
		return livre;
	}

	public void setLivre(LivreDTO livreDTO) {
		this.livre = livreDTO;
	}

	public String getEtat() {
		return etat;
	}

	public void setEtat(String etat) {
		this.etat = etat;
	}

	@Override
	public String toString() {
		return "ExemplaireDTO [id=" + id + ", livreDTO=" + livre + ", etat=" + etat + "]";
	}
}
