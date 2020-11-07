package com.oc.projets.projet_10.restController;

import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import javax.validation.constraints.Size;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
//import org.springframework.security.access.method.P;
import org.springframework.security.access.prepost.PreAuthorize;
//import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.oc.projets.projet_10.conversion.ConversionEmprunt;
import com.oc.projets.projet_10.conversion.ConversionUsager;
import com.oc.projets.projet_10.dto.EmpruntDTO;
import com.oc.projets.projet_10.dto.UsagerDTO;
import com.oc.projets.projet_10.dto.UsagerGetDTO;
import com.oc.projets.projet_10.entity.Emprunt;
import com.oc.projets.projet_10.entity.Usager;
import com.oc.projets.projet_10.service.EmpruntService;
import com.oc.projets.projet_10.service.UsagerConnecteService;
import com.oc.projets.projet_10.service.UsagerDetails;
import com.oc.projets.projet_10.service.UsagerService;

@RestController
@CrossOrigin
@RequestMapping("/api/usager")
public class UsagerRestController {

	@Autowired
	private UsagerService usagerService;
	
	@Autowired
	private UsagerConnecteService usagerConnecteService;
	
	@Autowired
	private EmpruntService empruntService;
	
	@Autowired
	private ConversionUsager conversionUsager;
	
	@Autowired
	private ConversionEmprunt conversionEmprunt;
	
	@GetMapping("")
	@PreAuthorize("hasAuthority('ADMIN')")
	public ResponseEntity<List<UsagerGetDTO>> getAllUsagers(){
		return ResponseEntity.ok(this.usagerService.getAllIdNot(this.usagerConnecteService.getUsagerConnecte().getId()));
	}
	
	/* Retourne un usager en fonction de son id */
	@GetMapping("/{id}")
	public ResponseEntity<UsagerGetDTO> findById(@PathVariable(value = "id") Long usagerId) {
		UsagerGetDTO usagerGetDTO = this.usagerService.getUsager(usagerId);
		if(usagerGetDTO != null) {
			return ResponseEntity.ok(usagerGetDTO);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		
	}
	
	/* Créer un usager */
	@PostMapping("/create")
	public ResponseEntity<UsagerDTO> createUsager(@RequestBody UsagerDTO usagerDTO) {
		try{
			usagerDTO = this.usagerService.createUsager(usagerDTO);
			return ResponseEntity.ok(usagerDTO);
		} catch(Exception e) {
			e.printStackTrace();
		}
		return ResponseEntity.status(HttpStatus.CONFLICT).body(usagerDTO);
	}
	
	@PostMapping("/createAdmin")
	@PreAuthorize("hasAuthority('ADMIN')")
	public ResponseEntity<UsagerDTO> createAdmin(@RequestBody UsagerDTO usagerDTO) {
		try{
			usagerDTO = this.usagerService.createAdmin(usagerDTO);
			return ResponseEntity.ok(usagerDTO);
		} catch(Exception e) {
			e.printStackTrace();
		}
		return ResponseEntity.status(HttpStatus.CONFLICT).body(usagerDTO);
	}
	
	@PutMapping("/update/role")
	@PreAuthorize("hasAuthority('ADMIN')")
	public ResponseEntity<UsagerGetDTO> editUsager(@RequestBody UsagerGetDTO usagerGetDTO) {
		return ResponseEntity.ok(this.usagerService.editRoleUsager(usagerGetDTO));
	}
	
	@PutMapping("/update/profil")
	@PreAuthorize("isAuthenticated()")
	public ResponseEntity<UsagerGetDTO> editProfilUsager(@RequestBody UsagerGetDTO usagerGetDTO) {
		return ResponseEntity.ok(this.usagerService.editProfilUsager(usagerGetDTO));
	}
	
	@GetMapping("/emprunts")
	@PreAuthorize("isAuthenticated()")
	public ResponseEntity<List<EmpruntDTO>> getEmprunts(){
		System.out.println("Size : " + this.empruntService.getEmpruntsUsagerConnecte().size());
		return ResponseEntity.ok(this.empruntService.getEmpruntsUsagerConnecte());
	}
	
	@GetMapping("/connecte")
	public ResponseEntity<UsagerGetDTO> getUsagerConnecte() {
		return ResponseEntity.ok(this.usagerConnecteService.getUsagerConnecte());
	}
}
