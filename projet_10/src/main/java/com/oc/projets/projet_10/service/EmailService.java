package com.oc.projets.projet_10.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.stereotype.Service;

import com.oc.projets.projet_10.entity.Reservation;

@Service
public class EmailService {
	
	@Autowired
	private JavaMailSender javaMailSender;

	public void transformReservation(Reservation reservation) {
		
		SimpleMailMessage msg = new SimpleMailMessage();
		msg.setTo("romaindemellier@gmail.com");
		msg.setSubject("Le livre " + reservation.getLivre().getTitre() + " vous est réservé");
		msg.setText("Bonjour " + reservation.getUsager().getPrenom() + ",\n" +
				"Vous avez 48 heures pour venir chercher le livre " + reservation.getLivre().getTitre() + ".\n" +
				"Suite à ce délai ce livre ne vous sera plus réservé.");
		
		//this.javaMailSender = new JavaMailSenderImpl();
		this.javaMailSender.send(msg);
	}

	public void sendMailIfStillReservations(Reservation reservation, Boolean isStillReservations){
		if(isStillReservations){
			System.out.println("Envoi d'un mail");
			this.transformReservation(reservation);
		}
	}
}
