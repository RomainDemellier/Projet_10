package com.oc.projets.projet_10.repository;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

import com.oc.projets.projet_10.entity.Livre;
import com.oc.projets.projet_10.entity.Reservation;
import com.oc.projets.projet_10.entity.Usager;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface ReservationRepository extends JpaRepository<Reservation, Long> {

    List<Reservation> findReservationByActif(Boolean actif);

    List<Reservation> findReservationsByLivreAndActifOrderByDateAsc(Livre livre, Boolean actif);
    
    List<Reservation> findReservationsByLivreAndActifAndDateLimitOrderByDateAsc(Livre livre, Boolean actif, LocalDate dateLimit);

    List<Reservation> findReservationsByUsagerAndActifOrderByDateAsc(Usager usager, Boolean actif);

    List<Reservation> findReservationsByLivreAndUsagerAndActifAndDateLimitNotNull(Livre livre, Usager usager, Boolean actif);
    
    @Query(value = "SELECT * FROM reservation WHERE actif = true AND date_limit IS NOT NULL AND date_limit <= current_timestamp ", nativeQuery = true)
    List<Reservation> findReservationDateLimitDepasser();

    int countReservationsByLivreAndUsagerAndActif(Livre livre, Usager usager, Boolean actif);
    
    int countReservationsByLivreAndActifAndDateLimit(Livre livre, Boolean actif, LocalDate dateLimit);

    //@Query(value = "SELECT COUNT (*) FROM reservation r WHERE r.livre_id = ?1 AND r.usager_id = ?2 AND r.actif = true AND r.dateLimit IS NOT NULL ", nativeQuery = true)
    //int countByLivreAndUsagerAndActifAndDateLimitNotNull(Long lid, Long uid);
    int countByLivreAndUsagerAndActifAndDateLimitNotNull(Livre livre, Usager usager, Boolean actif);

    int countReservationsByLivreAndActif(Livre livre, Boolean actif);

//    Pour calculer la place dans la liste des réservations
    int countReservationByLivreAndActifAndDateLessThan(Livre livre, Boolean actif, LocalDateTime date);
}
