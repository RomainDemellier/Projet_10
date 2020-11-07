package com.oc.projets.projet_10.repository;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.oc.projets.projet_10.entity.Emprunt;
import com.oc.projets.projet_10.entity.Usager;

@Repository
public interface EmpruntRepository extends JpaRepository<Emprunt, Long> {

	List<Emprunt> findByUsager(Usager usager);
	
	List<Emprunt> findByActif(Boolean actif);
	
	List<Emprunt> findByUsagerAndActif(Usager usager, Boolean actif);
	
	List<Emprunt> findByUsagerAndActifOrderByDateEmpruntAsc(Usager usager, Boolean actif);
	
	List<Emprunt> findByDateEmpruntAndActif(LocalDate date, Boolean actif);
	
	List<Emprunt> findAllByOrderByDateEmpruntDesc();
	
	@Query(value = "SELECT * FROM emprunt WHERE actif = true AND date_retour <= current_date ", nativeQuery = true)
	List<Emprunt> findEmpruntsRetard();
}
