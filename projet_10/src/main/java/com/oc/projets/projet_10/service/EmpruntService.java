package com.oc.projets.projet_10.service;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import java.text.ParseException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.parameters.P;
import org.springframework.stereotype.Service;

import com.oc.projets.projet_10.conversion.ConversionEmprunt;
import com.oc.projets.projet_10.dto.EmpruntDTO;
import com.oc.projets.projet_10.entity.Emprunt;
import com.oc.projets.projet_10.entity.Exemplaire;
import com.oc.projets.projet_10.entity.Livre;
import com.oc.projets.projet_10.entity.Usager;
import com.oc.projets.projet_10.exception.EmpruntException;
import com.oc.projets.projet_10.exception.ProlongationException;
import com.oc.projets.projet_10.exception.ResourceNotFoundException;
import com.oc.projets.projet_10.repository.EmpruntRepository;
import com.sun.istack.FinalArrayList;

import lombok.experimental.var;

@Service
public class EmpruntService {

	@Autowired
	private EmpruntRepository empruntRepository;
	
	Logger logger = LoggerFactory.getLogger(EmpruntService.class);
	
	@Autowired
	private ExemplaireService exemplaireService;
	
	@Autowired
	private LivreService livreService;
	
	@Autowired
	private ConversionEmprunt conversionEmprunt;
	
	@Autowired
	private UsagerService usagerService;
	
	@Autowired
	private UsagerConnecteService usagerConnecteService;
	
	@Autowired
	private ReservationService reservationService;
	
	public Emprunt findById(Long id) {
		
		logger.info("Début de findById. Argument type Long : " + id);
		logger.info("Retourne un objet type Emprunt.");
		
		return this.empruntRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Emprunt", "id", id));
	}
	
	public EmpruntDTO getById(Long empruntId) {
		
		logger.info("Début de getById. Argument type Long : " + empruntId);
		logger.info("Retourne un objet type EmpruntDTO.");
		
		return this.conversionEmprunt.convertToDto(this.findById(empruntId));
	}
	
	public List<EmpruntDTO> getListEmprunts(){
		
		logger.info("Début de getListEmprunts pas d'arguments");
		
		List<Emprunt> emprunts = this.empruntRepository.findAllByOrderByDateEmpruntDesc();
		
		logger.info("Fin de getListEmprunts. Retourne une liste d'emprunts.");
		
		return emprunts.stream().map(emprunt -> this.conversionEmprunt.convertToDto(emprunt)).collect(Collectors.toList());
	}

	public List<EmpruntDTO> getEmpruntsActif(){
		List<Emprunt> emprunts = this.empruntRepository.findByActif(true);
		return emprunts.stream().map(emprunt -> this.conversionEmprunt.convertToDto(emprunt)).collect(Collectors.toList());
	}
	
	public EmpruntDTO create(EmpruntDTO empruntDTO){
		
		logger.info("Début de la méthode create. Prend un argument type EmpruntDTO : " + empruntDTO.toString());
		
		Emprunt emprunt = this.conversionEmprunt.convertToEntity(empruntDTO);
		//Usager usager = this.usagerConnecteService.authentification();
		Usager usager = this.usagerService.findById(emprunt.getUsager().getId());
		emprunt.setUsager(usager);
		
		LocalDate localDate = LocalDate.now();
			
		emprunt.setDateEmprunt(localDate);
			
		LocalDate localDate2 = localDate.plusDays(28);
			
		emprunt.setDateRetour(localDate2);
			
		emprunt.setProlonge(false);
			
		emprunt.setActif(true);

		Exemplaire exemplaire = this.exemplaireService.findById(emprunt.getExemplaire().getId());
		Livre livre = exemplaire.getLivre();
		livre.setNbreExemplaires(livre.getNbreExemplaires() - 1);
		this.livreService.editLivre(livre);
		emprunt.setExemplaire(exemplaire);

		this.reservationService.deleteReservationIfEmprunt(livre,usager);

		this.empruntRepository.save(emprunt);

//		this.setDateRetourLaPlusProche(emprunt);

		empruntDTO = this.conversionEmprunt.convertToDto(emprunt);
		
		logger.info("Fin de la méthode create. Retourne un EmpruntDTO : " + empruntDTO.toString());
		
		return empruntDTO;
	}

	public EmpruntDTO delete(Emprunt emprunt) {
		
		logger.info("Début de la méthode delete. Prend un argumnet type Emprunt : " + emprunt);
		
		emprunt.setActif(false);
		this.empruntRepository.save(emprunt);

//		this.setDateRetourLaPlusProche(emprunt);

		Livre livre = emprunt.getExemplaire().getLivre();
		Boolean isStillReservations = this.reservationService.isStillReservations(livre);
		System.out.println("Is reservation : " + isStillReservations);
		this.reservationService.setDateLimitIfReservation(livre,isStillReservations);
		
		logger.info("Fin de la méthode delete. Retourne un EmpruntDTO : " + this.conversionEmprunt.convertToDto(emprunt).toString());
		
		return this.conversionEmprunt.convertToDto(emprunt);
	}

	public EmpruntDTO findBYIdAndProlonger(Long empruntId) throws EmpruntException, ProlongationException {
		Emprunt emprunt = this.findById(empruntId);
		try {
			emprunt = this.prolonger(emprunt);
			this.empruntRepository.save(emprunt);
//			this.setDateRetourLaPlusProche(emprunt);
			return conversionEmprunt.convertToDto(emprunt);
		} catch (EmpruntException e){
			throw  new EmpruntException(e.getMessage());
		} catch (ProlongationException e){
			throw new ProlongationException(e.getMessage());
		}
	}
	
	public Emprunt prolonger(Emprunt emprunt) throws EmpruntException, ProlongationException {
		
		logger.info("Début de la méthode prolonger. Prend un argument de type Long : " + emprunt.toString());
		
		//Emprunt emprunt = this.findById(empruntId);
		if(!emprunt.getProlonge()) {
			
			LocalDate localDate = LocalDate.now();
			LocalDate dateRetour = emprunt.getDateRetour();
			
			if(localDate.compareTo(dateRetour) < 0) {
				
				emprunt.setProlonge(true);
				emprunt.setDateRetour(emprunt.getDateEmprunt().plusDays(56));
				//this.empruntRepository.save(emprunt);
				
				logger.info("Fin de la méthode prolonger. Retourne un EmpruntDTO : " + this.conversionEmprunt.convertToDto(emprunt).toString());

				return emprunt;
				//return this.conversionEmprunt.convertToDto(emprunt);
			} else {
				logger.warn("Dans la méthode prolonger. Date butoir dépassée pour prolonger cet emprunt. Emprunt : " + emprunt.toString());
				
				throw new ProlongationException("Vous avez dépassé la date limite pour prolonger votre emprunt");
			}
			
		} else {
			
			logger.warn("Dans la méthode prolonger. Cet emprunt a déjà été prolonger. Emprunt : " + emprunt.toString());
			
			throw new EmpruntException("Vous avez déjà prolongé une fois l'emprunt de ce livre.");
		}
	}
	
	public List<EmpruntDTO> getEmpruntsUsagerConnecte() {
		
		logger.info("Début de la méthode getEmpruntsUsagerConnecte. Pas d'arguments");
		
		Usager usager = this.usagerConnecteService.authentification();
		List<Emprunt> emprunts = this.empruntRepository.findByUsagerAndActifOrderByDateEmpruntAsc(usager, true);
		
		logger.info("Fin de la méthode getEmpruntsConnecte. Retourne une liste d'emprunts List<Emprunt>");
		
		return emprunts.stream().map(emprunt -> this.conversionEmprunt.convertToDto(emprunt)).collect(Collectors.toList());
	}

	public List<Emprunt> findByUsagerAndActif(Usager usager){
		return this.empruntRepository.findByUsagerAndActif(usager,true);
	}
	
	public void dejaEnPossession(EmpruntDTO empruntDTO) throws EmpruntException {
		
		logger.info("Début de la méthode dejaEnPossession. Prend un argument de type EmpruntDTO : " + empruntDTO.toString());
		
		Usager usager = this.usagerService.findById(empruntDTO.getUsager().getId());
		List<Emprunt> emprunts = this.findByUsagerAndActif(usager);
		Exemplaire exemplaire = this.exemplaireService.findById(empruntDTO.getExemplaire().getId());
		for(int i = 0;i < emprunts.size();i++) {
			if(emprunts.get(i).getExemplaire().getLivre().getId().equals(exemplaire.getLivre().getId())) {
				
				logger.warn("Dans la méthode dejaEnPossession. L'usager essaie d'emprunter un livre dont il est déjà en possession.");
				
				throw new EmpruntException("Vous êtes déjà en possession de ce livre.");
			}
		}
		logger.info("Fin de la méthode dejaEnPossession. Ne retourne rien.");
	}
	
	public List<EmpruntDTO> getEmpruntsRetard(){
		//LocalDate date = LocalDate.now();
//		List<Emprunt> emprunts = this.empruntRepository.findByDateEmpruntAndActif(date, true);
		logger.info("Début de la méthode getEmpruntsRetard");
		List<Emprunt> emprunts = this.empruntRepository.findEmpruntsRetard();
		logger.info("Après liste : " + emprunts);
		return emprunts.stream().map(emprunt -> this.conversionEmprunt.convertToDto(emprunt)).collect(Collectors.toList());
	}
	
	private Date dateRetour(Date date, int nbreDays) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		calendar.add(Calendar.DATE, nbreDays);
		
		return calendar.getTime();
	}

	public int countEmpruntsByUsagerAndByLivre(Usager usager, Livre livre){
		return this.empruntRepository.countEmpruntsByUsagerAndLivreAndActif(usager.getId(),livre.getId());
	}

	public  LocalDate getDateRetourLaPlusProche(Long livreId){
		return this.empruntRepository.findDateRetourPlusProche(livreId);
	}
}
