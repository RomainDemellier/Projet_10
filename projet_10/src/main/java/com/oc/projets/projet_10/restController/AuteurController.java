package com.oc.projets.projet_10.restController;

import java.util.List;

import com.oc.projets.projet_10.exception.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import com.oc.projets.projet_10.dto.AuteurDTO;
import com.oc.projets.projet_10.entity.Auteur;
import com.oc.projets.projet_10.service.AuteurService;


@RestController
@CrossOrigin
@RequestMapping("api/auteur")
public class AuteurController {

	@Autowired
	private AuteurService auteurService;
	
	/* Créer un auteur */
	@PostMapping("/create")
	@PreAuthorize("hasAuthority('ADMIN')")
	public ResponseEntity<Auteur> createAuteur(@RequestBody Auteur auteur) {
		return ResponseEntity.ok(this.auteurService.createAuteur(auteur));
	}
	
	@GetMapping("")
	public ResponseEntity<List<AuteurDTO>> getAll(){
		return ResponseEntity.ok(this.auteurService.findAll());
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<AuteurDTO> getById(@PathVariable(value = "id") Long auteurId) {
		AuteurDTO auteurDTO = this.auteurService.findById(auteurId);
		if(auteurDTO != null) {
			return ResponseEntity.ok(auteurDTO);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}

	@PutMapping("/update/{id}")
	@PreAuthorize("hasAuthority('ADMIN')")
	public ResponseEntity<AuteurDTO> updateAuteur(@PathVariable(value = "id") Long auteurId, @RequestBody AuteurDTO auteurDTO){
		this.auteurService.update(auteurId,auteurDTO);
		if(auteurDTO != null){
			return ResponseEntity.ok(auteurDTO);
		}
		return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	}

	@DeleteMapping("/delete/{id}")
	@PreAuthorize("hasAuthority('ADMIN')")
	public ResponseEntity deleteAuteur(@PathVariable(value = "id") Long auteurId){
		try {
			this.auteurService.delete(auteurId);
			return new ResponseEntity(HttpStatus.OK);
		} catch (ResourceNotFoundException e) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}
}
