package com.oc.projets.projet_10.entity;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@Table(name = "emprunt")
@EntityListeners(AuditingEntityListener.class)
@JsonIgnoreProperties(value = {"createdAt", "updateAt"},
		allowGetters = true)
public class Emprunt implements Serializable {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@ManyToOne
	@JoinColumn(name = "exemplaire_id", nullable = false)
	private Exemplaire exemplaire;
	
    @ManyToOne
    @JoinColumn(name = "usager_id", nullable = false)
	private Usager usager;
	
	@Column(name="date_emprunt")
	private LocalDate dateEmprunt;
	
	@Column(name = "date_retour")
	private LocalDate dateRetour;
	
	private Boolean prolonge;
	
	private Boolean actif;

	public Emprunt() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Exemplaire getExemplaire() {
		return exemplaire;
	}

	public void setExemplaire(Exemplaire exemplaire) {
		this.exemplaire = exemplaire;
	}

	public Usager getUsager() {
		return usager;
	}

	public void setUsager(Usager usager) {
		this.usager = usager;
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
		return "Emprunt [id=" + id + ", exemplaire=" + exemplaire + ", usager=" + usager + ", dateEmprunt="
				+ dateEmprunt + ", dateRetour=" + dateRetour + ", prolonge=" + prolonge + ", actif=" + actif + "]";
	}
}
