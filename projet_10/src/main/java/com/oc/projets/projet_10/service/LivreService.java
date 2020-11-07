package com.oc.projets.projet_10.service;

import java.util.List;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.oc.projets.projet_10.conversion.ConversionLivre;
import com.oc.projets.projet_10.dto.LivreCreationDTO;
import com.oc.projets.projet_10.dto.LivreDTO;
import com.oc.projets.projet_10.entity.Exemplaire;
import com.oc.projets.projet_10.entity.Livre;
import com.oc.projets.projet_10.exception.EmpruntException;
import com.oc.projets.projet_10.exception.ResourceNotFoundException;
import com.oc.projets.projet_10.repository.LivreRepository;

@Service
public class LivreService {

	@Autowired
	private LivreRepository livreRepository;
	
	@Autowired
	private ConversionLivre conversionLivre;
	
	Logger logger = LoggerFactory.getLogger(LivreService.class);
	
	public Livre createLivre(LivreDTO livreDTO) {
		
		logger.info("Début de la méthode createLivre. Prend en argument de type LivreCreationDTO : " + livreDTO.toString());
		
		Livre livre = this.conversionLivre.convertToEntity(livreDTO);
		//livre.setFullNameAuteur(livre.getAuteur().getPrenom() + ' ' + livre.getAuteur().getNom());
		livre = this.livreRepository.save(livre);
		
		logger.info("Fin de la méthode createLivre. Retourne un Livre : " + livre.toString());
		
		return livre;
	}
	
	public Livre editLivre(Livre livre) {
		
		logger.info("Début de la méthode editLivre. Prend un argument de type Livre : " + livre.toString());
		
		livre = this.livreRepository.save(livre);
		
		logger.info("Fin de la méthode editLivre. Retourne un Livre : "+ livre.toString());
		
		return livre;
	}
	
	public LivreDTO editNbreExemplaires(LivreDTO livreDTO) {
		
		logger.info("Début de la méthode editNbreExemplaires. Prend un argument de type LivreDTO : " + livreDTO.toString());
		
		Livre livre = this.findById(livreDTO.getId());
		livre.setNbreExemplaires(livreDTO.getNbreExemplaires());
		livre = this.editLivre(livre);
		
		logger.info("Fin de la méthode editNbreExemplaires. Retourne un LivreDTO" + this.conversionLivre.convertToDTO(livre).toString());
		
		return this.conversionLivre.convertToDTO(livre);
	}
	
	public LivreDTO getLivre(Long id) {
		
		logger.info("Début de la méthode getLivre. Prend un argument de type Long : " + id);
		
		Livre livre = this.findById(id);
		
		logger.info("Fin de la méthode getLivre. Retourne un LivreDTO : " + this.conversionLivre.convertToDTO(livre).toString());
		
		return this.conversionLivre.convertToDTO(livre);
	}
	
	public List<LivreDTO> getAllLivres(){
		
		logger.info("Début de la méthode getAllLivres. Pas d'arguments.");
		
		List<Livre> livres = this.livreRepository.findAllByOrderByTitreAsc();
		
		
		logger.info("Fin de la méthode getAllLivres. Retourne une liste List<LivreDTO>.");
		
		return livres.stream().map(livre -> this.conversionLivre.convertToDTO(livre)).collect(Collectors.toList());
	}
	
	public Livre findById(Long livreId) {
		
		logger.info("Début de la méthode findById. Prend un argument de type Long : " + livreId);
		
		logger.info("Fin de la méthode findById. Retourne un Livre.");
		
		return this.livreRepository.findById(livreId).orElseThrow(() -> new ResourceNotFoundException("Livre", "id", livreId));
				
	}
	
	public void estDisponible(Long livreId) throws EmpruntException {
		
		logger.info("Début de la méthode estDisponible. Prend un argument de type Long : " + livreId);
		
		Livre livre = this.findById(livreId);
		int nbreExemplaires = livre.getNbreExemplaires();
		System.out.println("nbreExemplaires : " + nbreExemplaires);
		if(nbreExemplaires <= 0) {
			
			logger.warn("Ce livre n'est pas disponible. Livre : " + livre);
			
			throw new EmpruntException("Ce livre n'est pas disponible pour le moment.");
		} 
		
		logger.info("Fin de la méthode estDisponible. Ne retourne rien.");
	}
	
	public void rendre(Livre livre) {
		
		logger.info("Début de la méthode rendre. Prend un argument de type Livre : " + livre.toString());
		
		int nbreExemplaires = livre.getNbreExemplaires();
		livre.setNbreExemplaires(nbreExemplaires + 1);
		this.editLivre(livre);
		
		logger.info("Fin de la méthode rendre. Ne retourne rien");
	}
}
