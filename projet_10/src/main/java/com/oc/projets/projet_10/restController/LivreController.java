package com.oc.projets.projet_10.restController;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

import com.oc.projets.projet_10.conversion.ConversionAuteur;
import com.oc.projets.projet_10.exception.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import com.oc.projets.projet_10.conversion.ConversionLivre;
import com.oc.projets.projet_10.dto.LivreCreationDTO;
import com.oc.projets.projet_10.dto.LivreDTO;
import com.oc.projets.projet_10.entity.Auteur;
import com.oc.projets.projet_10.entity.Exemplaire;
import com.oc.projets.projet_10.entity.Livre;
import com.oc.projets.projet_10.service.AuteurService;
import com.oc.projets.projet_10.service.LivreService;

@RestController
@CrossOrigin
@RequestMapping("/api/livre")
public class LivreController {

	@Autowired
	private LivreService livreService;
	
	@Autowired
	private ConversionLivre conversionLivre;
	
	/* Retourne la liste de tous les livres */
	@GetMapping("")
	public ResponseEntity<List<LivreDTO>> getAll(){
		return ResponseEntity.ok(this.livreService.getAllLivres());
	}
	
	/* Retourne un livre en fonction de son id */
	@GetMapping("/{id}")
	public ResponseEntity<LivreDTO> getById(@PathVariable(value = "id") Long livreId) {
		LivreDTO livreDTO = this.livreService.getLivre(livreId);
		if(livreDTO != null) {
			return ResponseEntity.ok(livreDTO);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}
	
	/* Créer un livre */
	@PostMapping("/create")
	@PreAuthorize("hasAuthority('ADMIN')")
	public ResponseEntity<Livre> createLivre(@RequestBody LivreDTO livre) {
		return ResponseEntity.ok(this.livreService.createLivre(livre));
	}
	
	@PatchMapping("/editNbreExemplaires/{id}")
	@PreAuthorize("hasAuthority('ADMIN')")
	public ResponseEntity<LivreDTO> editNbreExemplaires(@PathVariable(value = "id") Long id, @RequestBody LivreDTO livreDTO) {
		return ResponseEntity.ok(this.livreService.editNbreExemplaires(id,livreDTO));
	}

	@GetMapping("/dateRetourPlusProche/{id}")
	public ResponseEntity<LocalDate> getDateRetourPlusProche(@PathVariable Long id){
		return ResponseEntity.ok(this.livreService.getDateRetourPlusProche(id));
	}

	@PutMapping("/update/{id}")
	@PreAuthorize("hasAuthority('ADMIN')")
	public ResponseEntity<LivreDTO> update(@PathVariable(value = "id") Long id, @RequestBody LivreDTO livreDTO){
		try {
			return ResponseEntity.ok(this.livreService.update(id,livreDTO));
		} catch (Exception e){
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}

	@DeleteMapping("/delete/{id}")
	@PreAuthorize("hasAuthority('ADMIN')")
	public ResponseEntity delete(@PathVariable(value = "id") Long id){
		try {
			this.livreService.delete(id);
			return ResponseEntity.ok(HttpStatus.OK);
		} catch (ResourceNotFoundException e){
			return new ResponseEntity(HttpStatus.NOT_FOUND);
		}
	}

	@GetMapping("/isReservable/{id}")
	public ResponseEntity isReservable(@PathVariable(value = "id") Long id){
		return ResponseEntity.ok((this.livreService.isLivreReservable(id)));
	}
}
