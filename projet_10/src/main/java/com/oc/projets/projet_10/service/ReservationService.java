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

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;



@Service
public class ReservationService {

    Logger logger = LoggerFactory.getLogger(ReservationService.class);

    @Autowired
    private ReservationRepository reservationRepository;

    @Autowired
    private LivreService livreService;

    @Autowired
    private UsagerService usagerService;

    @Autowired
    private EmpruntService empruntService;

    @Autowired
    private UsagerConnecteService usagerConnecteService;

    @Autowired
    private ConversionReservation conversionReservation;
    
    @Autowired
    private EmailService emailService;

    @Autowired
    private ConversionUsager conversionUsager;

    public void saveReservation(Reservation reservation){
        this.reservationRepository.save(reservation);
    }

    public Reservation findById(Long id){
        
        return this.reservationRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Reservation", "id", id));
    }

    public ReservationDTO getById(Long id){
        Reservation reservation = this.findById(id);
        return this.conversionReservation.convertToDto(reservation);
    }

    public Reservation create(Livre livre, Usager usager, ReservationDTO reservationDTO) throws ReservationException {
        if(!this.isLivreReservable(livre)){
            throw new ReservationException("Ce livre ne peut pas être réservé");
        }
        if(this.allReadyReserved(livre,usager)){
            throw new ReservationException("Vous avez déjà réservé ce livre");
        }
        if(this.checkIfEmpruntEnCours(usager,livre)){
            throw new ReservationException("Vous ne pouvez pas réserver ce livre, parce que vous avez un emprunt en cours avec ce livre.");
        }

        Reservation reservation = this.conversionReservation.convertToEntity(reservationDTO);

        reservation.setUsager(usager);
        reservation.setLivre(livre);
        LocalDateTime localDate = LocalDateTime.now();

        System.out.println("Date : " + localDate);

        reservation.setDate(localDate);

        reservation.setDateLimit(null);

        reservation.setActif(true);

        this.setNbreReservationsForLivre(livre,"+");

        return reservation;
    }

    public ReservationDTO getLivreAndUsagerAndCreate(ReservationDTO reservationDTO) throws ReservationException {

        Livre livre = livreService.findById(reservationDTO.getLivre().getId());
        Usager usager = this.usagerConnecteService.authentification();

        try {
            Reservation reservation = this.create(livre,usager,reservationDTO);
            reservation = this.reservationRepository.save(reservation);

            int nbreReservations = this.numberOfReservations(livre);
            System.out.println("nbrereservation : " + nbreReservations);
            this.livreService.editLivre(livre);
            return this.conversionReservation.convertToDto(reservation);
        } catch (ReservationException e) {
            throw new ReservationException(e.getMessage());
/*        } catch (UsagerException e) {
            throw new UsagerException(e.getMessage());*/
        }
    }

    public void editReservation(Reservation reservation){
        this.reservationRepository.save(reservation);
    }

    public Boolean isLivreReservable(Livre livre){
        int nbreReservations = this.countReservationsByLivreAndActif(livre);
        if(nbreReservations >= livre.getNbreTotal()*2 || livre.getNbreExemplaires() > 0){
            return false;
        }
        return true;
    }

    public Boolean allReadyReserved(Livre livre, Usager usager) {
        if(this.numberReservationForLivreAndUsager(livre,usager) > 0){
            return true;
        }
        return false;
    }

    public Boolean checkIfEmpruntEnCours(Usager usager, Livre livre) {
        if(this.empruntService.countEmpruntsByUsagerAndByLivre(usager,livre) > 0){
            return true;
        }
        return false;
    }

    public int numberReservationForLivreAndUsager(Livre livre, Usager usager)  {
        System.out.println("Dans number reservations : " + this.reservationRepository.countReservationsByLivreAndUsagerAndActif(livre,usager,true));
        return this.reservationRepository.countReservationsByLivreAndUsagerAndActif(livre,usager,true);
    }

    public List<Reservation> reservationForLivreAndUsager(Livre livre, Usager usager){
        return  this.reservationRepository.findReservationsByLivreAndUsagerAndActifAndDateLimitNotNull(livre,usager,true);
    }

    public List<ReservationDTO> getAllReservations(){

        logger.info("Début de la méthode getAllReservations. Pas d'arguments");

        List<Reservation> reservations = this.reservationRepository.findAll();

        logger.info("Fin de la méthode getAllReservations. Retourne une liste List<ReservationDTO>.");

        return reservations.stream().map(reservation -> this.conversionReservation.convertToDto(reservation)).collect(Collectors.toList());
    }

    public List<ReservationDTO> getAllReservationsActif(){

        logger.info("Début de la méthode getAllReservations. Pas d'arguments");

        List<Reservation> reservations = this.reservationRepository.findReservationByActif(true);

        logger.info("Fin de la méthode getAllReservations. Retourne une liste List<ReservationDTO>.");

        return reservations.stream().map(reservation -> this.conversionReservation.convertToDto(reservation)).collect(Collectors.toList());
    }

    public List<ReservationDTO> getReservationsUsagerConnecte(){
        Usager usager = this.usagerConnecteService.authentification();
        List<Reservation> reservations = this.reservationRepository.findReservationsByUsagerAndActifOrderByDateAsc(usager,true);
        return reservations.stream().map(reservation -> this.conversionReservation.convertToDto(reservation)).collect(Collectors.toList());
    }

    public ReservationDTO findAndUpdate(Long id){
        try {
            Reservation reservation = this.findById(id);
            reservation = this.setDateLimit(reservation);
            this.reservationRepository.save(reservation);
            return  this.conversionReservation.convertToDto(reservation);
        } catch (ResourceNotFoundException e) {
            //TODO: handle exception
            throw new ResourceNotFoundException("Reservation", "id", id);
        }
    }

    public Reservation setDateLimit(Reservation reservation) {

            LocalDateTime dateLimit = LocalDateTime.now().plusDays(2);
            int minutes = dateLimit.getMinute();
            dateLimit = dateLimit.minusMinutes(minutes);
            dateLimit = dateLimit.plusHours(1);
            reservation.setDateLimit(dateLimit);
            //this.reservationRepository.save(reservation);
            return reservation;
    }

    public ReservationDTO findAndDelete(Long id) throws ResourceNotFoundException {
        try {
            Reservation reservation = this.findById(id);
            return this.delete(reservation);
        } catch (ResourceNotFoundException e){
            throw new ResourceNotFoundException("Reservation", "id", id);
        }
    }

    public ReservationDTO delete(Reservation reservation)  {

        Livre livre = reservation.getLivre();
        this.setActif(reservation);
        this.saveReservation(reservation);
        this.setNbreReservationsForLivre(livre,"-");
        if(reservation.getDateLimit() != null){
            Boolean isStillReservation  = this.isStillReservations(livre);
            System.out.println("isStillReservation : " + isStillReservation);
            this.setDateLimitIfReservation(livre,isStillReservation);
            this.livreService.rendreLivreIfNoReservations(livre,isStillReservation);
            this.livreService.editLivre(livre);
            this.sendEmailForReservation(reservation,isStillReservation);
        }
        return this.conversionToDTO(reservation);
    }

    public void setDateLimitIfReservation(Livre livre, Boolean isStillReservations){
        if(isStillReservations) {
            List<Reservation> reservations = this.reservationRepository.findReservationsByLivreAndActifAndDateLimitOrderByDateAsc(livre, true, null);
            Reservation reservation = reservations.get(0);
            this.findAndUpdate(reservation.getId());
            this.sendEmailForReservation(reservation);
        }
    }

    public void sendEmailForReservation(Reservation reservation, Boolean isStillReservation){
        if(isStillReservation){
            this.emailService.transformReservation(reservation);
        }
    }

    public void sendEmailForReservation(Reservation reservation){
        this.emailService.transformReservation(reservation);
    }
    
    public List<ReservationDTO> getReservationDateLimitDepasse(){
    	List<Reservation> reservations = this.reservationRepository.findReservationDateLimitDepasser();
    	//System.out.println(reservations.size());
    	return reservations.stream().map(reservation -> this.conversionReservation.convertToDto(reservation)).collect(Collectors.toList());
    }

    public Boolean isStillReservations(Livre livre){
        //return this.reservationRepository.countByLivreAndActifAndDateLimit(livre,true,null) > 0 ? true : false;
        return this.numberOfReservationsDateLimitNull(livre) > 0;
    }

    public int numberOfReservations(Livre livre){
        return this.reservationRepository.countReservationsByLivreAndActif(livre, true);
    }

    public int numberOfReservationsDateLimitNull(Livre livre){
        return this.reservationRepository.countReservationsByLivreAndActifAndDateLimit(livre, true,null);
    }

    public void setActif(Reservation reservation){
        reservation.setActif(false);
    }

    public ReservationDTO conversionToDTO(Reservation reservation){
        return this.conversionReservation.convertToDto(reservation);
    }

    public void setNbreReservationsForLivre(Livre livre, String type){
        this.livreService.setNbreReservations(livre,type);
    }

    public void deleteReservationIfEmprunt(Livre livre, Usager usager){
        List<Reservation> r = this.reservationForLivreAndUsager(livre,usager);
        int nbre = r.size();
        if(nbre != 0){
            this.delete(r.get(0));
        }
    }

    public int placeIntoListReservations(Long idReservation){
        try {
            Reservation reservation = this.findById(idReservation);
            Livre livre = reservation.getLivre();
            LocalDateTime dateTime = reservation.getDate();
            return this.reservationRepository.countReservationByLivreAndActifAndDateLessThan(livre,true,dateTime) + 1;
        } catch (ResourceNotFoundException e){
            throw new ResourceNotFoundException("Reservation", "id", idReservation);
        }
    }

    public int countReservationsByLivreAndActif(Livre livre){
        return this.reservationRepository.countReservationsByLivreAndActif(livre,true);
    }
}
