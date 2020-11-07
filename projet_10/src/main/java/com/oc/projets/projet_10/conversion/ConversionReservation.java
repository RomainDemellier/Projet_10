package com.oc.projets.projet_10.conversion;

import com.oc.projets.projet_10.dto.ReservationDTO;
import com.oc.projets.projet_10.entity.Reservation;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ConversionReservation {
    
    @Autowired
    private ModelMapper modelMapper;

    public ConversionReservation(){

    }

    public ReservationDTO convertToDto(Reservation reservation) {
		ReservationDTO reservationDTO = modelMapper.map(reservation, ReservationDTO.class);
		return reservationDTO;
	}
	
	public Reservation convertToEntity(ReservationDTO reservationDTO) {
		Reservation reservation = modelMapper.map(reservationDTO, Reservation.class);
		return reservation;
	}
}