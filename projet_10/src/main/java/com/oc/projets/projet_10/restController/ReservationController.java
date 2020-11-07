package com.oc.projets.projet_10.restController;

import com.oc.projets.projet_10.dto.ReservationDTO;
import com.oc.projets.projet_10.entity.Reservation;
import com.oc.projets.projet_10.exception.ResourceNotFoundException;
import com.oc.projets.projet_10.service.ReservationService;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin
@RequestMapping("/api/reservation")
public class ReservationController {
    
    @Autowired
    private ReservationService reservationService;

    @GetMapping("/{id}")
    public ResponseEntity<ReservationDTO> getReservation(@PathVariable(value = "id") Long id){
        try {
            ReservationDTO reservationDTO = this.reservationService.getById(id);
            return ResponseEntity.ok(reservationDTO);
        } catch (ResourceNotFoundException e) {
            //TODO: handle exception
            e.printStackTrace();
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @GetMapping("")
    public ResponseEntity<List<ReservationDTO>> getAllReservations(){
        return ResponseEntity.ok(this.reservationService.getAllReservations());
    }

    @PostMapping("/create")
    public ResponseEntity<ReservationDTO> create(ReservationDTO reservationDTO){
        try {
            
            return ResponseEntity.ok(this.reservationService.create(reservationDTO));
        } catch (Exception e) {
            //TODO: handle exception
            e.printStackTrace();
        }
        return ResponseEntity.status(HttpStatus.CONFLICT).body(reservationDTO);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<ReservationDTO> update(@PathVariable(value = "id") Long id){
        try {
            ReservationDTO reservationDTO = this.reservationService.update(id);
            return ResponseEntity.ok(reservationDTO);
        } catch (ResourceNotFoundException e) {
            //TODO: handle exception
            e.printStackTrace();
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<ReservationDTO> delete(@PathVariable(value = "id") Long id){
        try {
            Reservation reservation = this.reservationService.findById(id);
            return ResponseEntity.ok(this.reservationService.delete(reservation));
        } catch (ResourceNotFoundException e) {
            //TODO: handle exception
            e.printStackTrace();
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
}