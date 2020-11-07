package com.oc.projets.projet_10.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.oc.projets.projet_10.entity.Exemplaire;
import com.oc.projets.projet_10.entity.Livre;

@Repository
public interface LivreRepository extends JpaRepository<Livre, Long> {

	List<Livre> findAllByOrderByTitreAsc();
}
