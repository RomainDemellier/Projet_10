package com.oc.projets.projet_10.restController;

import com.oc.projets.projet_10.exception.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import com.oc.projets.projet_10.conversion.ConversionExemplaire;
import com.oc.projets.projet_10.dto.ExemplaireDTO;
import com.oc.projets.projet_10.entity.Exemplaire;
import com.oc.projets.projet_10.service.ExemplaireService;

import java.util.List;

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

	@GetMapping("")
	public ResponseEntity<List<ExemplaireDTO>> getAllExemplaires(){
		return ResponseEntity.ok(this.exemplaireService.getAllExemplaires());
	}

	@GetMapping("/{id}")
	public ResponseEntity<ExemplaireDTO> get(@PathVariable(value = "id") Long id){
		try {
			ExemplaireDTO exemplaireDTO = this.exemplaireService.getExemplaire(id);
			return ResponseEntity.ok(exemplaireDTO);
		} catch (ResourceNotFoundException e){
			return  new ResponseEntity(HttpStatus.NOT_FOUND);
		}
	}

	@PutMapping("/update/{id}")
	@PreAuthorize("hasAuthority('ADMIN')")
	public ResponseEntity<ExemplaireDTO> update(@PathVariable(value = "id") Long id, @RequestBody ExemplaireDTO exemplaireDTO){
		try {
			exemplaireDTO = this.exemplaireService.update(id,exemplaireDTO);
			return ResponseEntity.ok(exemplaireDTO);
		} catch (ResourceNotFoundException e){
			return new ResponseEntity(HttpStatus.NOT_FOUND);
		}
	}

	@DeleteMapping("/delete/{id}")
	@PreAuthorize("hasAuthority('ADMIN')")
	public ResponseEntity delete(@PathVariable(value = "id") Long id){
		try {
			this.exemplaireService.delete(id);
			return ResponseEntity.ok(HttpStatus.OK);
		} catch (ResourceNotFoundException e){
			return new ResponseEntity(HttpStatus.NOT_FOUND);
		}
	}
}
