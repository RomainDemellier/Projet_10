package com.oc.projets.projet_10.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.oc.projets.projet_10.entity.Exemplaire;

@Repository
public interface ExemplaireRepository extends JpaRepository<Exemplaire, Long> {

}
