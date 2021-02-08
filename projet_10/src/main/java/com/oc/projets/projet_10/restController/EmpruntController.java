package com.oc.projets.projet_10.restController;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Calendar;
import java.util.Date;
import java.util.Iterator;
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

import com.oc.projets.projet_10.dto.EmpruntDTO;
import com.oc.projets.projet_10.dto.LivreDTO;
import com.oc.projets.projet_10.dto.UsagerGetDTO;
import com.oc.projets.projet_10.entity.Emprunt;
import com.oc.projets.projet_10.entity.Exemplaire;
import com.oc.projets.projet_10.entity.Usager;
import com.oc.projets.projet_10.exception.EmpruntException;
import com.oc.projets.projet_10.exception.ProlongationException;
import com.oc.projets.projet_10.repository.EmpruntRepository;
import com.oc.projets.projet_10.service.EmpruntService;
import com.oc.projets.projet_10.service.ExemplaireService;
import com.oc.projets.projet_10.service.LivreService;
import com.oc.projets.projet_10.service.UsagerService;


@RestController
@CrossOrigin
@RequestMapping("/api/emprunt")
public class EmpruntController {

	@Autowired
	private UsagerService usagerService;
	
	@Autowired
	private LivreService livreService;
	
	@Autowired
	private ExemplaireService exemplaireService;
	
	@Autowired
	private EmpruntService empruntService;
	
	@GetMapping("/{id}")
	public ResponseEntity<EmpruntDTO> getEmprunt(@PathVariable(value = "id") Long empruntId) {
		EmpruntDTO empruntDTO = this.empruntService.getById(empruntId);
		if(empruntDTO != null) {
			return ResponseEntity.ok(empruntDTO);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}
	
	@GetMapping("")
	@PreAuthorize("hasAuthority('ADMIN')")
	public ResponseEntity<List<EmpruntDTO>> getListEmprunts(){
		return ResponseEntity.ok(this.empruntService.getListEmprunts());
	}
	
	@PostMapping("/create")
	//@PreAuthorize("isAuthenticated()")
	@PreAuthorize("hasAuthority('ADMIN')")
	public ResponseEntity<EmpruntDTO> emprunter(@RequestBody EmpruntDTO empruntDTO) {
		
		try {
			this.empruntService.dejaEnPossession(empruntDTO);
			Long livreId = this.exemplaireService.findById(empruntDTO.getExemplaire().getId()).getLivre().getId();
//			this.livreService.estDisponible(empruntDTO.getExemplaireDTO().getLivre().getId());
			this.livreService.estDisponible(livreId);
			return ResponseEntity.ok(this.empruntService.create(empruntDTO));
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return ResponseEntity.status(HttpStatus.CONFLICT).body(empruntDTO);
	}
	
	@PutMapping("/prolonger/{id}")
	@PreAuthorize("isAuthenticated()")
	public ResponseEntity<EmpruntDTO> prolonger(@PathVariable(value = "id") Long empruntId) {
		try{
			return ResponseEntity.ok(this.empruntService.prolonger(empruntId));
		} catch(ProlongationException e) {
			e.printStackTrace();
			// error.status 403
			return ResponseEntity.status(HttpStatus.FORBIDDEN).body(null);
		} catch (EmpruntException e) {
			e.printStackTrace();
			// error.status 409
			return ResponseEntity.status(HttpStatus.CONFLICT).body(null);
		}
		
	}
	
	@DeleteMapping("/delete/{id}")
	@PreAuthorize("isAuthenticated()")
	public ResponseEntity<EmpruntDTO> rendre(@PathVariable(value = "id") Long empruntId) {
		Emprunt emprunt = this.empruntService.findById(empruntId);
		//this.livreService.rendre(emprunt.getExemplaire().getLivre());
		return ResponseEntity.ok(this.empruntService.delete(emprunt));
	}
	
	@GetMapping("/batch")
	public ResponseEntity<List<EmpruntDTO>> getEmpruntsRetard(){
		return ResponseEntity.ok(this.empruntService.getEmpruntsRetard());
	}
}
