package com.oc.projets.projet_10;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;

@SpringBootApplication
public class Projet7Application {
//public class Projet7Application extends SpringBootServletInitializer {

//	Déploiement
//	  @Override
//	  protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
//	    return application.sources(Projet7Application.class);
//	  }
	
	public static void main(String[] args) {
		SpringApplication.run(Projet7Application.class, args);
	}

}
