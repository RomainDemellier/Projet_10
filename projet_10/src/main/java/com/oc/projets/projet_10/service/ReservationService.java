package com.oc.projets.projet_10.service;

import com.oc.projets.projet_10.conversion.ConversionReservation;
import com.oc.projets.projet_10.conversion.ConversionUsager;
import com.oc.projets.projet_10.dto.LivreDTO;
import com.oc.projets.projet_10.dto.ReservationDTO;
import com.oc.projets.projet_10.entity.Emprunt;
import com.oc.projets.projet_10.entity.Livre;
import com.oc.projets.projet_10.entity.Reservation;
import com.oc.projets.projet_10.entity.Usager;
import com.oc.projets.projet_10.exception.ReservationException;
import com.oc.projets.projet_10.exception.ResourceNotFoundException;
import com.oc.projets.projet_10.exception.UsagerException;
import com.oc.projets.projet_10.repository.ReservationRepository;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;



@Service
public class ReservationService {
    
    @Autowired
    private ReservationRepository reservationRepository;

    @Autowired
    private LivreService livreService;

    @Autowired
    private UsagerService usagerService;

    @Autowired
    private UsagerConnecteService usagerConnecteService;

    @Autowired
    private ConversionReservation conversionReservation;
    
    @Autowired
    private EmailService emailService;

    @Autowired
    private ConversionUsager conversionUsager;

    public Reservation findById(Long id){
        
        return this.reservationRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Reservation", "id", id));
    }

    public ReservationDTO getById(Long id){
        Reservation reservation = this.findById(id);
        return this.conversionReservation.convertToDto(reservation);
    }

    public ReservationDTO create(ReservationDTO reservationDTO) throws ReservationException, UsagerException {

                
                    Livre livre = livreService.findById(reservationDTO.getLivre().getId());
                    if(livre.isReservable() && livre.getNbreExemplaires() <= 0){
                        Usager usager = this.usagerConnecteService.authentification();

                        List<Reservation> reservations = this.reservationRepository.findByLivreAndUsagerAndActif(livre, usager, true);
            
                        Reservation reservation = this.conversionReservation.convertToEntity(reservationDTO);
                        if(reservations.size() > 0){
                            throw new UsagerException("Vous avez déjà réservé ce livre");
                        }
                        reservation.setUsager(usager);
                        reservation.setLivre(livre);
                        LocalDateTime localDate = LocalDateTime.now();
                        
                        System.out.println("Date : " + localDate);
            
                        reservation.setDate(localDate);
                
                        reservation.setDateLimit(null);
            
                        reservation.setActif(true);
                
                        reservation = this.reservationRepository.save(reservation);

                        int nbreReservations = this.numberOfReservations(livre);
                        System.out.println("nbrereservation : " + nbreReservations);
                        if(nbreReservations >= 2*livre.getNbreTotal()){
                            livre.setReservable(false);
                            this.livreService.editLivre(livre);
                        }
                
                        return this.conversionReservation.convertToDto(reservation);

                    } else {
                        int nbreReservations = this.numberOfReservations(livre);
                        System.out.println("nbrereservation : " + nbreReservations);
                        throw new ReservationException("Ce livre ne peut pas être réservé");
                    }
    }

    public List<ReservationDTO> getAllReservations(){
        List<Reservation> reservations = this.reservationRepository.findAll();

        return reservations.stream().map(reservation -> this.conversionReservation.convertToDto(reservation)).collect(Collectors.toList());
    }

    public List<ReservationDTO> getReservationsUsagerConnecte(){
        Usager usager = this.usagerConnecteService.authentification();
        List<Reservation> reservations = this.reservationRepository.findByUsagerAndActifOrderByDateAsc(usager,true);
        return reservations.stream().map(reservation -> this.conversionReservation.convertToDto(reservation)).collect(Collectors.toList());
    }

    public List<ReservationDTO> getReservationsByLivre(LivreDTO livreDTO){
        Livre livre = this.livreService.findById(livreDTO.getId());
        List<Reservation> reservations = this.reservationRepository.findByLivreAndActifOrderByDateAsc(livre, true);
        return reservations.stream().map(reservation -> this.conversionReservation.convertToDto(reservation)).collect(Collectors.toList());
    }

    public ReservationDTO update(Long id) {
        try {
            Reservation reservation = this.findById(id);
//            LocalDateTime dateLimit = LocalDateTime.now().plusDays(2);
//            int minutes = dateLimit.getMinute();
//            dateLimit = dateLimit.minusMinutes(minutes);
//            dateLimit = dateLimit.plusHours(1);
            LocalDateTime dateLimit = LocalDateTime.now();
            reservation.setDateLimit(dateLimit);
            this.reservationRepository.save(reservation);
            return this.conversionReservation.convertToDto(reservation);
        } catch (ResourceNotFoundException e) {
            //TODO: handle exception
            throw new ResourceNotFoundException("Reservation", "id", id);
        }
    }

    public ReservationDTO delete(Long id) throws ReservationException {
    	try {
    		Reservation reservation = this.findById(id);
    		Livre livre = reservation.getLivre();
    		reservation.setActif(false);
    		this.reservationRepository.save(reservation);
    		this.setDateLimitIfReservation(livre);
    		if(!livre.isReservable()) {
    			livre.setReservable(true);
    		}
    		this.livreService.editLivre(livre);
    		return this.conversionReservation.convertToDto(reservation);
		} catch (ResourceNotFoundException e) {
			// TODO: handle exception
			throw new ReservationException("Impossible de supprimer cette réservation.");
		}
//        this.reservationRepository.delete(reservation);
//        return this.conversionReservation.convertToDto(reservation);
    } 
    
    public void setDateLimitIfReservation(Livre livre) {
    	//Livre livre = emprunt.getExemplaire().getLivre();
    	int nbre = this.reservationRepository.countByLivreAndActifAndDateLimit(livre, true, null);
    	List<Reservation> reservations = new ArrayList<Reservation>();
    	if(nbre > 0) {
    		reservations = this.reservationRepository.findByLivreAndActifAndDateLimitOrderByDateAsc(livre, true, null);
    		Reservation reservation = reservations.get(0);
    		this.update(reservation.getId());
    		this.emailService.transformReservation(reservation);
    	} else {
    		this.livreService.rendre(livre);
    	}
    }
    
    public List<ReservationDTO> getReservationDateLimitDepasse(){
    	List<Reservation> reservations = this.reservationRepository.findReservationDateLimitDepasser();
    	//System.out.println(reservations.size());
    	return reservations.stream().map(reservation -> this.conversionReservation.convertToDto(reservation)).collect(Collectors.toList());
    }
    
    private Usager getUsagerConnecte(){
        return this.conversionUsager.convertToEntity(this.usagerConnecteService.getUsagerConnecte());
    }

    private int numberOfReservations(Livre livre){
        return this.reservationRepository.countByLivreAndActif(livre, true);
    }
}