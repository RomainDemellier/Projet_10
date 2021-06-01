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

/*	@Query(value = "SELECT em.date_retour FROM emprunt em INNER JOIN exemplaire ex ON em.exemplaire_id = ex.id WHERE em.actif = true AND ex.livre_id = ?1 AND em.date_retour <= ALL " +
			"(SELECT em.date_retour FROM emprunt em2 INNER JOIN exemplaire ex2 ON em2.exemplaire_id = ex2.id WHERE em2.actif = true AND ex2.livre_id = ?1)", nativeQuery = true)
	LocalDate findDateRetourPlusProche(Long lid);*/

	@Query(value = "SELECT distinct(em.date_retour) FROM emprunt em INNER JOIN exemplaire ex ON em.exemplaire_id = ex.id WHERE em.actif = true AND ex.livre_id = ?1 AND em.date_retour <= ALL " +
			"(SELECT em2.date_retour FROM emprunt em2 INNER JOIN exemplaire ex2 ON em2.exemplaire_id = ex2.id WHERE em2.actif = true AND ex2.livre_id = ?1)", nativeQuery = true)
	LocalDate findDateRetourPlusProche(Long lid);

	@Query(value = "SELECT COUNT(*) FROM emprunt em INNER JOIN exemplaire ex ON em.exemplaire_id = ex.id WHERE em.actif = true AND em.usager_id = ?1 AND ex.livre_id = ?2 ", nativeQuery = true)
	int countEmpruntsByUsagerAndLivreAndActif(Long uid, Long lid);

	@Query(value = "SELECT COUNT (*) FROM emprunt em INNER JOIN exemplaire ex ON em.exemplaire_id = ex.id WHERE em.actif = true AND ex.livre_id = ?1", nativeQuery = true)
	int countEmpruntsByLivreAndActif(Long lid);
}
