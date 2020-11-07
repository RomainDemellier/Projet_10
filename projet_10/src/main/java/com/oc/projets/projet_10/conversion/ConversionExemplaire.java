package com.oc.projets.projet_10.conversion;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.oc.projets.projet_10.dto.ExemplaireDTO;
import com.oc.projets.projet_10.entity.Exemplaire;

@Component
public class ConversionExemplaire {

	@Autowired
	private ModelMapper modelMapper;
	
	public ConversionExemplaire() {
		// TODO Auto-generated constructor stub
	}
	
	public ExemplaireDTO convertToDto(Exemplaire exemplaire) {
		ExemplaireDTO exemplaireDTO = modelMapper.map(exemplaire, ExemplaireDTO.class);
		return exemplaireDTO;
	}
	
	public Exemplaire convertToEntity(ExemplaireDTO exemplaireDTO) {
		Exemplaire exemplaire = modelMapper.map(exemplaireDTO, Exemplaire.class);
		return exemplaire;
	}
}
