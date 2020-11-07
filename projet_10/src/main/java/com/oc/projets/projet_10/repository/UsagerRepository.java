package com.oc.projets.projet_10.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.oc.projets.projet_10.entity.Usager;

@Repository
public interface UsagerRepository extends JpaRepository<Usager, Long> {

	Usager findByEmail(String email);
	
	List<Usager> findByIdNot(Long id);
}
