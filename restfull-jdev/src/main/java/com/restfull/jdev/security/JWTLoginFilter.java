package com.restfull.jdev.security;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AbstractAuthenticationProcessingFilter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.restfull.jdev.model.Usuario;

import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;

public class JWTLoginFilter extends AbstractAuthenticationProcessingFilter {

	/* Configurando o gerenciador de autenticacao */
	protected JWTLoginFilter(String url, AuthenticationManager authenticationManager) {

		/* Obriga a autenticar a URL */
		super(new AntPathRequestMatcher(url));

		/* Gerenciador de autenticacao */
		setAuthenticationManager(authenticationManager);

	}

	/* Retorna o usuário ao processar a autenticação */
	@Override
	public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response)
			throws AuthenticationException, IOException, ServletException {

		/* Está pegando o token para validar */
		Usuario user = new ObjectMapper().readValue(request.getInputStream(), Usuario.class);

		/* Retorna o usuario login, senha e acessos */
		return getAuthenticationManager()
				.authenticate(new UsernamePasswordAuthenticationToken(user.getLogin(), user.getSenha()));
	}

	@Override
	protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain,
			Authentication authResult) throws IOException, ServletException {

		new JWTTokenAutenticacaoService().addAuthentication((jakarta.servlet.http.HttpServletResponse) response, authResult.getName());

	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, jakarta.servlet.FilterChain chain)
			throws IOException, jakarta.servlet.ServletException {
		// TODO Auto-generated method stub
		
	}

}
