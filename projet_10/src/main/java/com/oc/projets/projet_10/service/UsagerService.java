package com.oc.projets.projet_10.service;

import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.oc.projets.projet_10.conversion.ConversionEmprunt;
import com.oc.projets.projet_10.conversion.ConversionLivre;
import com.oc.projets.projet_10.conversion.ConversionUsager;
import com.oc.projets.projet_10.dto.EmpruntDTO;
import com.oc.projets.projet_10.dto.LivreDTO;
import com.oc.projets.projet_10.dto.UsagerDTO;
import com.oc.projets.projet_10.dto.UsagerGetDTO;
import com.oc.projets.projet_10.entity.Emprunt;
import com.oc.projets.projet_10.entity.Exemplaire;
import com.oc.projets.projet_10.entity.Usager;
import com.oc.projets.projet_10.exception.EmpruntException;
import com.oc.projets.projet_10.exception.PasswordException;
import com.oc.projets.projet_10.exception.ResourceNotFoundException;
import com.oc.projets.projet_10.repository.EmpruntRepository;
import com.oc.projets.projet_10.repository.UsagerRepository;


@Service
public class UsagerService {

	@Autowired
	private UsagerRepository usagerRepository;
	
	@Autowired
	private ConversionUsager conversionUsager;
	
	Logger logger = LoggerFactory.getLogger(UsagerService.class);
	
	public UsagerDTO createUsager(UsagerDTO usagerDTO) throws PasswordException {
		
		logger.info("Début de la méthode createUsager. Prend un argument de type UsagerDTO : " + usagerDTO.toString());
		
		String password = usagerDTO.getPassword();
		String confirmPassword = usagerDTO.getConfirmPassword();
		
		if(password.length() < 8 || password.length() > 16) {
			throw new PasswordException("Le mot de passe doit comporter entre 8 et 16 caractères.");
		}
		
		if(!password.equals(confirmPassword)) {
			
			logger.warn("Méthode createUsager. Les deux mots de passe ne sont pas identiques");
			
			throw new PasswordException("Les deux mots de passe doivent être identiques.");
		}
		
		Usager usager = this.conversionUsager.convertToEntity(usagerDTO);
		
		BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
		String encodePwd = bCryptPasswordEncoder.encode(usager.getPassword());
		System.out.println("encodePwd : " + encodePwd);
		usager.setPassword(encodePwd);
		
		usager.setRole("USER");
		
		this.usagerRepository.save(usager);
		
		
		logger.warn("Fin de la méthode createUsager. Retourne un UsagerDTO : " + this.conversionUsager.convertToDto(usager).toString());
		
		return this.conversionUsager.convertToDto(usager);
	}
	
	public UsagerDTO createAdmin(UsagerDTO usagerDTO) throws PasswordException {
		
		logger.info("Début de la méthode createAdmin. Prend en argument un UsagerDTO : " + usagerDTO.toString());
		
		String password = usagerDTO.getPassword();
		String confirmPassword = usagerDTO.getConfirmPassword();
		
		if(password.length() < 8 || password.length() > 16) {
			throw new PasswordException("Le mot de passe doit comporter entre 8 et 16 caractères.");
		}
		
		if(!password.equals(confirmPassword)) {
			
			logger.warn("Méthode createAdmin. Les deux mots de passe ne sont pas identiques");
			
			throw new PasswordException("Les deux mots de passe doivent être identiques.");
		}
		
		Usager usager = this.conversionUsager.convertToEntity(usagerDTO);
		
		BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
		String encodePwd = bCryptPasswordEncoder.encode(usager.getPassword());
		System.out.println("encodePwd : " + encodePwd);
		usager.setPassword(encodePwd);
		
		usager.setRole("ADMIN");
		
		this.usagerRepository.save(usager);
		
		logger.info("Fin de la méthode createAdmin. Retourne un UsagerDTO : " + this.conversionUsager.convertToDto(usager).toString());
		
		return this.conversionUsager.convertToDto(usager);
	}
	
	public UsagerGetDTO editRoleUsager(UsagerGetDTO usagerGetDTO) {
		
		logger.info("Début de la méthode editRoleUsager. Prend un argument de type UsagerGetDTO : " + usagerGetDTO.toString());
		
		Usager usager = this.findById(usagerGetDTO.getId());
		usager.setRole(usagerGetDTO.getRole());
		System.out.println("password : " + usager.getPassword().length());
		usager = this.usagerRepository.save(usager);
		
		logger.info("Fin de la méthode editRoleUsager. Retourne un UsagerGetDTO : " + this.conversionUsager.convertToGetDTO(usager).toString());
		
		return this.conversionUsager.convertToGetDTO(usager);
	}
	
	public UsagerGetDTO editProfilUsager(UsagerGetDTO usagerGetDTO) {
		
		logger.info("Début de la méthode editProfilUsager. Prend un argument de type UsagerGetDTO : " + usagerGetDTO.toString());

		Usager usager = this.findById(usagerGetDTO.getId());
		usager.setPrenom(usagerGetDTO.getPrenom());
		usager.setNom(usagerGetDTO.getNom());
		usager.setEmail(usagerGetDTO.getEmail());
		usager = this.usagerRepository.save(usager);
		
		logger.info("Fin de la méthode editProfilUsager. Retourne un UsagerGetDTO : " + this.conversionUsager.convertToGetDTO(usager).toString());

		return this.conversionUsager.convertToGetDTO(usager);
	}
	
	public Usager findById(Long id) {
		
		logger.info("Début de la méthode findById. Prend un argument de type Long : " + id);
		logger.info("Fin de la méthode findById. Retourne un Usager.");
		
		return this.usagerRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Usager","id",id));
	}
	
	public Usager findByEmail(String email) {
		
		logger.info("Début de la méthode findByEmail. Prend un argument de type String : " + email);
		logger.info("Fin de la méthode findByEmail. Retourne un Usager.");
		
		return this.usagerRepository.findByEmail(email);
	}
	
	public UsagerGetDTO getUsager(Long id) {
		
		logger.info("Début de la méthode getUsager. Prend un argument de type Long : " + id);
		
		Usager usager = this.findById(id);
		
		logger.info("Fin de la méthode getUsager. Retourne un UsagerGetDTO : " + this.conversionUsager.convertToGetDTO(usager).toString());
		
		return this.conversionUsager.convertToGetDTO(usager);
	}
	
	public List<UsagerGetDTO> getAll(){
		
		logger.info("Début de la méthode getAll. Pas d'arguments.");
		
		List<Usager> usagers = this.usagerRepository.findAll();
		
		logger.info("Fin de la méthode getAll. Retourne une liste List<UsagerGetDTO>.");
		
		return usagers.stream().map(usager -> this.conversionUsager.convertToGetDTO(usager)).collect(Collectors.toList());
	}
	
	public List<UsagerGetDTO> getAllIdNot(Long id){
		
		logger.info("Début de la méthode getAllIdNot. Prend un argument de type Long : " + id);
		
		List<Usager> usagers = this.usagerRepository.findByIdNot(id);
		Collections.sort(usagers, new Comparator<Usager>() {
			@Override
			public int compare(Usager u1, Usager u2) {
				return u1.getPrenom().compareTo(u2.getPrenom());
			}
		});
		
		logger.info("Fin de la méthode getAllIdNot. Retourne une liste List<UsagerGetDTO>.");
		
		return usagers.stream().map(usager -> this.conversionUsager.convertToGetDTO(usager)).collect(Collectors.toList());
	}

	public UsagerGetDTO update(Long id, UsagerGetDTO usagerGetDTO){
		Usager usager = this.findById(id);
		usager.setEmail(usagerGetDTO.getEmail());
		usager.setRole(usagerGetDTO.getRole());
		usager.setNom(usagerGetDTO.getNom());
		usager.setPrenom(usagerGetDTO.getPrenom());
		usager = this.usagerRepository.save(usager);
		return this.conversionUsager.convertToGetDTO(usager);
	}

	public void delete(Long id){
		Usager usager = this.findById(id);
		this.usagerRepository.delete(usager);
	}
}
