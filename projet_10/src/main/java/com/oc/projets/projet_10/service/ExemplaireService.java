package com.oc.projets.projet_10.service;

import java.util.List;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.oc.projets.projet_10.conversion.ConversionExemplaire;
import com.oc.projets.projet_10.dto.ExemplaireDTO;
import com.oc.projets.projet_10.dto.LivreDTO;
import com.oc.projets.projet_10.entity.Exemplaire;
import com.oc.projets.projet_10.entity.Livre;
import com.oc.projets.projet_10.exception.ResourceNotFoundException;
import com.oc.projets.projet_10.repository.ExemplaireRepository;

@Service
public class ExemplaireService {

	@Autowired
	private ExemplaireRepository exemplaireRepository;
	
	@Autowired
	private LivreService livreService;
	
	@Autowired
	private ConversionExemplaire conversionExemplaire;
	
	Logger logger = LoggerFactory.getLogger(EmpruntService.class);
	
	public Exemplaire createExemplaire(ExemplaireDTO exemplaireDTO) {
		
		logger.info("Début de la méthode createExemplaire. Prend un argument de type ExemplaireDTO : " + exemplaireDTO.toString());

		Livre livre = this.livreService.findById(exemplaireDTO.getLivre().getId());
		livre.setNbreExemplaires(livre.getNbreExemplaires() + 1);
		this.livreService.editLivre(livre);
		Exemplaire exemplaire = this.conversionExemplaire.convertToEntity(exemplaireDTO);
		exemplaire = this.exemplaireRepository.save(exemplaire);
		
		logger.info("Fin de la méthode createExemplaire. Retourne un Exemplaire : " + exemplaire.toString());
		
		return exemplaire;
	}
	
	public ExemplaireDTO getExemplaire(Long id) {
		
		logger.info("Début de la méthode getExemplaire. Prend un argument de type Long : " + id);
		
		Exemplaire exemplaire = this.exemplaireRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Exemplaire", "id", id));
		
		logger.info("Fin de la méthode getExemplaire. Retourne un ExemplaireDTO : " + this.conversionExemplaire.convertToDto(exemplaire));
		
		return this.conversionExemplaire.convertToDto(exemplaire);
	}
	
	public Exemplaire findById(Long id) {
		
		logger.info("Début de la méthode findById. Prend un argument de type Long : " + id);
		
		logger.info("Fin de la méthode findById. Retourne un Exemplaire.");
		
		return this.exemplaireRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Exemplaire", "id", id));

	}
	
	public List<ExemplaireDTO> getAllExemplaires(){
		
		logger.info("Début de la méthode getAllExemplaires. Pas d'arguments.");
		
		List<Exemplaire> exemplaires = this.exemplaireRepository.findAll();
		
		
		logger.info("Fin de la méthode getAllExemplaires. Retourne une liste List<ExemplaireDTO>.");
		
		return exemplaires.stream().map(exemplaire -> this.conversionExemplaire.convertToDto(exemplaire)).collect(Collectors.toList());
	}
}
