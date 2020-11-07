package com.oc.projets.projet_10.restController;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
	
	@PutMapping("/editNbreExemplaires")
	@PreAuthorize("hasAuthority('ADMIN')")
	public ResponseEntity<LivreDTO> editNbreExemplaires(@RequestBody LivreDTO livreDTO) {
		return ResponseEntity.ok(this.livreService.editNbreExemplaires(livreDTO));
	}
}
