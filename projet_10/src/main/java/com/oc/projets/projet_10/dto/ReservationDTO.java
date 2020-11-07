package com.oc.projets.projet_10.dto;

import java.io.Serializable;
import java.util.Date;

public class ReservationDTO implements Serializable {
    
    private Long id;

    private LivreDTO livre;

    private UsagerDTO usager;

    private Date date;

    private Date dateLimit;


    public ReservationDTO() {
    }

    public ReservationDTO(Long id, LivreDTO livre, UsagerDTO usager, Date date, Date dateLimit) {
        this.id = id;
        this.livre = livre;
        this.usager = usager;
        this.date = date;
        this.dateLimit = dateLimit;
    }

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

    public Date getDate() {
        return this.date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Date getDateLimit() {
        return this.dateLimit;
    }

    public void setDateLimit(Date dateLimit) {
        this.dateLimit = dateLimit;
    }
} 