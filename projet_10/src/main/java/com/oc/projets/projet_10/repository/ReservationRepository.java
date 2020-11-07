package com.oc.projets.projet_10.repository;

import java.util.List;

import com.oc.projets.projet_10.entity.Livre;
import com.oc.projets.projet_10.entity.Reservation;
import com.oc.projets.projet_10.entity.Usager;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface ReservationRepository extends JpaRepository<Reservation, Long> {

    List<Reservation> findByLivre(Livre livre);

    List<Reservation> findByUsager(Usager usager);

    List<Reservation> findByLivreAndUsager(Livre livre, Usager usager);

    int countByLivre(Livre livre);
}