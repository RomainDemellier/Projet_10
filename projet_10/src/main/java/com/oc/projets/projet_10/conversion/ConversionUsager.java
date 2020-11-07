package com.oc.projets.projet_10.conversion;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.oc.projets.projet_10.dto.UsagerDTO;
import com.oc.projets.projet_10.dto.UsagerGetDTO;
import com.oc.projets.projet_10.entity.Usager;

@Component
public class ConversionUsager {

	@Autowired
	private ModelMapper modelMapper;
	
	public ConversionUsager() {
		
	}
	
	public UsagerDTO convertToDto(Usager usager) {
		UsagerDTO usagerDTO = modelMapper.map(usager, UsagerDTO.class);
		return usagerDTO;
	}
	
	public Usager convertToEntity(UsagerDTO usagerDTO) {
		Usager usager = modelMapper.map(usagerDTO, Usager.class);
		return usager;
	}
	
	public UsagerGetDTO convertToGetDTO(Usager usager) {
		UsagerGetDTO usagerGetDTO = modelMapper.map(usager, UsagerGetDTO.class);
		return usagerGetDTO;
	}
	
	public Usager convertToEntity(UsagerGetDTO usagerGetDTO) {
		Usager usager = modelMapper.map(usagerGetDTO, Usager.class);
		return usager;
	}
}
