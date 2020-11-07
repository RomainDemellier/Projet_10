package com.oc.projets.projet_10.dto;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Date;

public class EmpruntDTO implements Serializable {

	private Long id;
	
	private ExemplaireDTO exemplaire;
	
	private UsagerGetDTO usager;
	
	private LocalDate dateEmprunt;
	
	private LocalDate dateRetour;
	
	private Boolean prolonge;
	
	private Boolean actif;
	
	public EmpruntDTO() {
		
	}	

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public ExemplaireDTO getExemplaire() {
		return exemplaire;
	}

	public void setExemplaire(ExemplaireDTO exemplaireDTO) {
		this.exemplaire = exemplaireDTO;
	}

	public UsagerGetDTO getUsager() {
		return usager;
	}

	public void setUsager(UsagerGetDTO usagerGetDTO) {
		this.usager = usagerGetDTO;
	}

	public LocalDate getDateEmprunt() {
		return dateEmprunt;
	}

	public void setDateEmprunt(LocalDate dateEmprunt) {
		this.dateEmprunt = dateEmprunt;
	}
	

	public Boolean getActif() {
		return actif;
	}

	public void setActif(Boolean actif) {
		this.actif = actif;
	}

	public LocalDate getDateRetour() {
		return dateRetour;
	}

	public Boolean getProlonge() {
		return prolonge;
	}

	public void setDateRetour(LocalDate dateRetour) {
		this.dateRetour = dateRetour;
	}

	public void setProlonge(Boolean prolonge) {
		this.prolonge = prolonge;
	}

	@Override
	public String toString() {
		return "EmpruntDTO [id=" + id + ", exemplaireDTO=" + exemplaire + ", usager=" + usager + ", dateEmprunt="
				+ dateEmprunt + ", dateRetour=" + dateRetour + ", prolonge=" + prolonge + ", actif=" + actif + "]";
	}
}
