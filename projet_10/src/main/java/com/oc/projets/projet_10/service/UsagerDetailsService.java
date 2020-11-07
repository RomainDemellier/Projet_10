package com.oc.projets.projet_10.service;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.oc.projets.projet_10.entity.Usager;
import com.oc.projets.projet_10.repository.UsagerRepository;

@Service
public class UsagerDetailsService implements UserDetailsService {

	@Autowired
	private UsagerRepository usagerRepository;
	
	@Override
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
		// TODO Auto-generated method stub
		Usager usager = this.usagerRepository.findByEmail(email);
		
		GrantedAuthority authority = new SimpleGrantedAuthority(usager.getRole());;
		
		UserDetails userDetails = (UserDetails) new User(usager.getEmail(),usager.getPassword(), Arrays.asList(authority));
		
		return userDetails;
	}

}
