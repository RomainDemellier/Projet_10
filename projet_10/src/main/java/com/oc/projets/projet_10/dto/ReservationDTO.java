package com.oc.projets.projet_10.dto;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;

public class ReservationDTO implements Serializable {
    
    private Long id;

    private LivreDTO livre;

    private UsagerDTO usager;

    private LocalDateTime date;

    private LocalDateTime dateLimit;

    public Boolean actif;

    public ReservationDTO() {
    }

//    public ReservationDTO(Long id, LivreDTO livre, UsagerDTO usager, Date date, Date dateLimit) {
//        this.id = id;
//        this.livre = livre;
//        this.usager = usager;
//        this.date = date;
//        this.dateLimit = dateLimit;
//    }

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LivreDTO getLivre() {
        return this.livre;
    }

    public void setLivre(LivreDTO livre) {
        this.livre = livre;
    }

    public UsagerDTO getUsager() {
        return this.usager;
    }

    public void setUsager(UsagerDTO usager) {
        this.usager = usager;
    }

    public LocalDateTime getDate() {
        return this.date;
    }

    public void setDate(LocalDateTime date) {
        this.date = date;
    }

    public LocalDateTime getDateLimit() {
        return this.dateLimit;
    }

    public void setDateLimit(LocalDateTime dateLimit) {
        this.dateLimit = dateLimit;
    }

    public Boolean getActif() {
        return actif;
    }

    public void setActif(Boolean actif) {
        this.actif = actif;
    }


    @Override
    public String toString() {
        return "ReservationDTO{" +
                "id=" + id +
                ", livre=" + livre +
                ", usager=" + usager +
                ", date=" + date +
                ", dateLimit=" + dateLimit +
                ", actif=" + actif +
                '}';
    }
}
