package com.oc.projets.projet_10.restController;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.oc.projets.projet_10.conversion.ConversionExemplaire;
import com.oc.projets.projet_10.dto.ExemplaireDTO;
import com.oc.projets.projet_10.entity.Exemplaire;
import com.oc.projets.projet_10.service.ExemplaireService;

@RestController
@CrossOrigin
@RequestMapping("/api/exemplaire")
public class ExemplaireController {

	@Autowired
	private ExemplaireService exemplaireService;
	
	@Autowired
	private ConversionExemplaire conversionExemplaire;
	
	@PostMapping("/create")
	@PreAuthorize("hasAuthority('ADMIN')")
	public ResponseEntity<Exemplaire> createExemplaire(@RequestBody ExemplaireDTO exemplaireDTO){
		//System.out.println("Dans createExemplaire id livre : " + exemplaireDTO.getLivre().getId());
		return ResponseEntity.ok(this.exemplaireService.createExemplaire(exemplaireDTO));
	}
}
