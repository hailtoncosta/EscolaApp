package com.app.minhaescolaapp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.core.Ordered;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@SpringBootApplication
@EnableWebMvc
public class MinhaEscolaAppApplication implements WebMvcConfigurer {

	public static void main(String[] args) {
		SpringApplication.run(MinhaEscolaAppApplication.class, args);
		
		/*
		BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
		String result = encoder.encode("31121976");
		System.out.println(result);
		*/
	}
	
	@Override
	public void addViewControllers(ViewControllerRegistry registry) {
		registry.addViewController("/login").setViewName("/login");
		registry.setOrder(Ordered.LOWEST_PRECEDENCE);
	} 

}
