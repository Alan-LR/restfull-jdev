package com.restfull.jdev;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@SpringBootApplication
//Cria todas tabelas de entidades
@EntityScan(basePackages = {"com.restfull.jdev.model"})
//Para injetar objetos onde quisermos
@ComponentScan(basePackages = {"com.*"})
//Indica onde esta nossas interfaces de persistencia
@EnableJpaRepositories(basePackages = {"com.restfull.jdev.repository"})
//Para nao enfrentar problemas de transacoes
@EnableTransactionManagement
//Habilitar MVC (vamos trabalhar somente com rest)
@EnableWebMvc
@RestController
//Configura tudo automaticamente
@EnableAutoConfiguration
public class RestfullJdevApplication implements WebMvcConfigurer{
	
	public static void main(String[] args) {
		SpringApplication.run(RestfullJdevApplication.class, args);
		System.out.println("Aplicação iniciada!!");
	}
	
	

}
