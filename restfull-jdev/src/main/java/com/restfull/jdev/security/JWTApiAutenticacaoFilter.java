package com.restfull.jdev.security;

import java.io.IOException;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.GenericFilterBean;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.http.HttpServletRequest;

/*Filtro onde todas as requisições serão capturadas para autenticar*/
public class JWTApiAutenticacaoFilter extends GenericFilterBean {

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		/* Estabelece a autenticação para requisição */
		Authentication authentication = new JWTTokenAutenticacaoService()
				.getAuhentication((HttpServletRequest) request);
		
		/*Coloca o processo de autenticação no spring security*/
		SecurityContextHolder.getContext().setAuthentication(authentication); 
		
		/*Continua o processo*/
		chain.doFilter(request, response);
	}

}
