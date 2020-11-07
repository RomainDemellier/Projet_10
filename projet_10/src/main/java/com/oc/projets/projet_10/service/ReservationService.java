package com.oc.projets.projet_10.service;

import com.oc.projets.projet_10.conversion.ConversionReservation;
import com.oc.projets.projet_10.dto.LivreDTO;
import com.oc.projets.projet_10.dto.ReservationDTO;
import com.oc.projets.projet_10.entity.Livre;
import com.oc.projets.projet_10.entity.Reservation;
import com.oc.projets.projet_10.entity.Usager;
import com.oc.projets.projet_10.exception.ResourceNotFoundException;
import com.oc.projets.projet_10.repository.ReservationRepository;
import java.time.LocalDate;
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

    public Reservation findById(Long id){
        
        return this.reservationRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Reservation", "id", id));
    }

    public ReservationDTO getById(Long id){
        Reservation reservation = this.findById(id);
        return this.conversionReservation.convertToDto(reservation);
    }

    public ReservationDTO create(ReservationDTO reservationDTO){

        Reservation reservation = this.conversionReservation.convertToEntity(reservationDTO);

        Usager usager = this.usagerService.findById(reservation.getUsager().getId());

        reservation.setUsager(usager);

        Livre livre = this.livreService.findById(reservation.getLivre().getId());

        LocalDate localDate = LocalDate.now();

        reservation.setDate(localDate);

        reservation.setDateLimit(null);

        this.reservationRepository.save(reservation);

        return this.conversionReservation.convertToDto(reservation);
    }

    public List<ReservationDTO> getAllReservations(){
        List<Reservation> reservations = this.reservationRepository.findAll();

        return reservations.stream().map(reservation -> this.conversionReservation.convertToDto(reservation)).collect(Collectors.toList());
    }

    public List<ReservationDTO> getReservationsUsagerConnecte(){
        Usager usager = this.usagerConnecteService.authentification();
        List<Reservation> reservations = this.reservationRepository.findByUsager(usager);
        return reservations.stream().map(reservation -> this.conversionReservation.convertToDto(reservation)).collect(Collectors.toList());
    }

    public List<ReservationDTO> getReservationsByLivre(LivreDTO livreDTO){
        Livre livre = this.livreService.findById(livreDTO.getId());
        List<Reservation> reservations = this.reservationRepository.findByLivre(livre);
        return reservations.stream().map(reservation -> this.conversionReservation.convertToDto(reservation)).collect(Collectors.toList());
    }

    public ReservationDTO update(Long id) {
        try {
            Reservation reservation = this.findById(id);
            reservation.setDateLimit(LocalDate.now().plusDays(2));
            this.reservationRepository.save(reservation);
            return this.conversionReservation.convertToDto(reservation);
        } catch (ResourceNotFoundException e) {
            //TODO: handle exception
            throw new ResourceNotFoundException("Reservation", "id", id);
        }
    }

    public ReservationDTO delete(Reservation reservation){
        this.reservationRepository.delete(reservation);
        return this.conversionReservation.convertToDto(reservation);
    }   
}