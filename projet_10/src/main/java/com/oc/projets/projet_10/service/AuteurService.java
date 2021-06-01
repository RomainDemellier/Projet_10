package com.oc.projets.projet_10.service;

import java.util.List;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.oc.projets.projet_10.conversion.ConversionAuteur;
import com.oc.projets.projet_10.dto.AuteurDTO;
import com.oc.projets.projet_10.entity.Auteur;
import com.oc.projets.projet_10.exception.AuteurException;
import com.oc.projets.projet_10.exception.ResourceNotFoundException;
import com.oc.projets.projet_10.repository.AuteurRepository;

@Service
public class AuteurService {

	@Autowired
	private AuteurRepository auteurRepository;
	
	@Autowired
	private ConversionAuteur conversionAuteur;
	
	Logger logger = LoggerFactory.getLogger(AuteurService.class);
	
	public Auteur createAuteur(Auteur auteur) {
		
		logger.info("Début de la méthode createAuteur. Prend un argument de type Auteur : " + auteur.toString());
		logger.info("Fin de la méthode createAuteur. Retourne un Auteur : " + auteur.toString());
		
		return this.auteurRepository.save(auteur);
	}
	
	public AuteurDTO findById(Long id) {
		
		logger.info("Début de la méthode findById. Prend un argument de type Long : " + id);
		
		Auteur auteur = this.auteurRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Auteur", "id", id));
		
		logger.info("Fin de la méthode findById. Retourne un AuteurDTO : " + this.conversionAuteur.convertToDTO(auteur).toString());
		
		return this.conversionAuteur.convertToDTO(auteur);
	}
	
	public List<AuteurDTO> findAll(){
		
		logger.info("Début de la méthode findAll. Pas d'arguments");
		
		List<Auteur> auteurs = this.auteurRepository.findAll();
		logger.info("Fin de la méthode findAll. Retourne une liste List<AuteurDTO>");
		return auteurs.stream().map(auteur -> this.conversionAuteur.convertToDTO(auteur)).collect(Collectors.toList());
	}

	public AuteurDTO update(Long id,AuteurDTO auteurDTO){
		auteurDTO.setId(id);
		Auteur auteur = this.conversionAuteur.convertToEntity(auteurDTO);
		auteur = this.auteurRepository.save(auteur);
		return this.conversionAuteur.convertToDTO(auteur);
	}

	public void delete(Long id){
		Auteur auteur = this.auteurRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Auteur", "id", id));
		this.auteurRepository.delete(auteur);
	}
}
