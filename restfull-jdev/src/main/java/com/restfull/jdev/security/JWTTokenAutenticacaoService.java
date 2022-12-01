package com.restfull.jdev.security;

import java.util.Date;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import com.restfull.jdev.ApplicationContextLoad;
import com.restfull.jdev.model.Usuario;
import com.restfull.jdev.repository.UsuarioRepository;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Service
@Component
public class JWTTokenAutenticacaoService {

	/* Tempo de validade do token = 2 dias */
	private static final long EXPIRATION_TIME = 172800000;

	/* Uma senha unica para ajudar a compor a autenticação e ajudar na segurança */
	private static final String SECRET = "SenhaExtremamenteSecreta";

	/* Prefixo padrão de token */
	private static final String TOKEN_PREFIX = "Bearer";

	private static final String HEADER_STRING = "Authorization";

	/* Gerando token de autenticação, adicionando ao cabeçalho e resposta HTTP */
	public void addAuthentication(HttpServletResponse response, String username) throws Exception {
		/* Montagem do token */
		String JWT = Jwts.builder() /* Chama o gerador de token */
				.setSubject(username) /* Adiciona o usuário */
				.setExpiration(new Date(System.currentTimeMillis() + EXPIRATION_TIME)) /* Tempo de expiração */
				.signWith(SignatureAlgorithm.HS512, SECRET).compact(); /* Geração de senha e compactação */
		/* Junta token com o prefixo */
		String token = TOKEN_PREFIX + " " + JWT; /* Bearer 8798789as78fg7as9fs8as8eeww */

		/* Adiciona no cabeçalho HTTP */
		response.addHeader(HEADER_STRING, token); /* Authorization: Bearer 8798789as78fg7as9fs8as8eeww */

		/* Escreve token como resposta no cabeçalho HTTP */
		response.getWriter().write("{\"Authorization\": \"" + token + "\"}");
	}

	/* Retorna o usuário validado com token, caso não seja valido retorna null */
	public Authentication getAuthentication(HttpServletRequest request) {
		/* Pega o token enviado no cabeçalho HTTP */
		String token = request.getHeader(HEADER_STRING);
		if (token != null) {
			/* Faz a validação do token do usuário na requisição */
			String user = Jwts.parser().setSigningKey(SECRET) /* Bearer 8798789as78fg7as9fs8as8eeww */
					.parseClaimsJws(token.replace(TOKEN_PREFIX, "")) /* 8798789as78fg7as9fs8as8eeww */
					.getBody().getSubject(); /* Nome do usuário, ex: João silva */
			if (user != null) {
				Usuario usuario = ApplicationContextLoad.getApplicationContext().getBean(UsuarioRepository.class)
						.findUserByLogin(user);

				if (usuario != null) {
					return new UsernamePasswordAuthenticationToken(usuario.getLogin(), usuario.getSenha(),
							usuario.getAuthorities());
				}
			}
		}
		return null; /* Não autorizado */
	}
}
