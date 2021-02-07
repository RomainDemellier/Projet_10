package com.oc.projets.projet_10.repository;

import java.time.LocalDate;
import java.util.List;

import com.oc.projets.projet_10.entity.Livre;
import com.oc.projets.projet_10.entity.Reservation;
import com.oc.projets.projet_10.entity.Usager;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface ReservationRepository extends JpaRepository<Reservation, Long> {

    List<Reservation> findByLivreAndActifOrderByDateAsc(Livre livre, Boolean actif);
    
    List<Reservation> findByLivreAndActifAndDateLimitOrderByDateAsc(Livre livre, Boolean actif, LocalDate dateLimit);

    List<Reservation> findByUsagerAndActifOrderByDateAsc(Usager usager, Boolean actif);

    List<Reservation> findByLivreAndUsagerAndActif(Livre livre, Usager usager, Boolean actif);
    
    @Query(value = "SELECT * FROM reservation WHERE actif = true AND date_limit IS NOT NULL AND date_limit <= current_timestamp ", nativeQuery = true)
    List<Reservation> findReservationDateLimitDepasser();

    int countByLivreAndActif(Livre livre, Boolean actif);
    
    int countByLivreAndActifAndDateLimit(Livre livre, Boolean actif, LocalDate dateLimit);
    
}