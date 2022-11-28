package com.restfull.controller;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.restfull.jdev.model.Usuario;

@RestController
@RequestMapping(value = "/usuario")
public class IndexController {

	//? retorna algo generico, a partir disso podemos retornar qualquer coisa, até itens diferentes
	@GetMapping(value = "/", produces = "application/json")
	public ResponseEntity<?> testeParametros(
			@RequestParam(value = "nome", required = true, defaultValue = "Nome não informado!") String nome,
			@RequestParam(name = "salario") String salario) {
		return new ResponseEntity("Olá mundo!, meu nome é: " + nome + " ,e o seu salário é: " + salario, HttpStatus.OK);
	}
	
	@GetMapping(value = "/criados" , produces = "application/json")
	public ResponseEntity<List<Usuario>> init(){
		Usuario usuario = new Usuario(10L, "Alan", "alan123", "123@456");
		Usuario usuario2 = new Usuario(11L, "Ana", "ana123", "123@456");
		List<Usuario> usuarios = new ArrayList<Usuario>(Arrays.asList(usuario, usuario2));
		return new ResponseEntity<List<Usuario>>(usuarios, HttpStatus.OK);
	}

}
