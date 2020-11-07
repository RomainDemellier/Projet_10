package com.oc.projets.projet_10.conversion;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.oc.projets.projet_10.dto.EmpruntDTO;
import com.oc.projets.projet_10.entity.Emprunt;

@Component
public class ConversionEmprunt {

	@Autowired
	private ModelMapper modelMapper;
	
	public ConversionEmprunt() {
		
	}
	
	public EmpruntDTO convertToDto(Emprunt emprunt) {
		EmpruntDTO empruntDTO = modelMapper.map(emprunt, EmpruntDTO.class);
		return empruntDTO;
	}
	
	public Emprunt convertToEntity(EmpruntDTO empruntDTO) {
		Emprunt emprunt = modelMapper.map(empruntDTO, Emprunt.class);
		return emprunt;
	}
}
