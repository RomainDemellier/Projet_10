package com.oc.projets.projet_10.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import com.oc.projets.projet_10.conversion.ConversionUsager;
import com.oc.projets.projet_10.dto.UsagerGetDTO;
import com.oc.projets.projet_10.entity.Usager;
import com.oc.projets.projet_10.repository.UsagerRepository;

import net.bytebuddy.asm.Advice.This;

@Service
public class UsagerConnecteService {

	private Usager usager = null;
	
	@Autowired
	private UsagerRepository usagerRepository;
	
	@Autowired
	private ConversionUsager conversionUsager;
	
	Logger logger = LoggerFactory.getLogger(UsagerConnecteService.class);
	
	public UsagerGetDTO getUsagerConnecte() {
		
		logger.info("Début de la méthode getUsagerConnecte. Pas d'arguments.");
		
		this.usager = this.authentification();
		
		logger.info("Fin de la méthode getUsagerConnecte. Retourne un UsagerGetDTO : " + this.conversionUsager.convertToDto(usager));
		
		return this.conversionUsager.convertToGetDTO(usager);
	}
	
    public Usager authentification() {
    	
    	logger.info("Début de la méthode authentication. Pas d'arguments.");
    	
		UserDetails user =  (UserDetails) SecurityContextHolder
				.getContext().getAuthentication().getPrincipal();
		
		Usager usager = this.usagerRepository.findByEmail(user.getUsername());
		
		logger.info("Fin de la méthode authentication. Retourne un Usager :  " + usager);
		
		return usager;
    }
}
