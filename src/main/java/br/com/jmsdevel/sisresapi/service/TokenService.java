package br.com.jmsdevel.sisresapi.service;

import java.util.Date;
import java.util.Optional;

import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTDecodeException;
import com.auth0.jwt.interfaces.DecodedJWT;

import br.com.jmsdevel.sisresapi.model.Usuario;

@Service
public class TokenService {
	private static final Algorithm algorithm = Algorithm.HMAC256("secret");
	
	
	public String geraToken(Authentication authentication) {
		
		Usuario logado = (Usuario) authentication.getPrincipal();
		
		String token = JWT
						.create()
						.withIssuer("SISRES-API")
						.withIssuedAt(new Date())
						.withSubject(logado.getId().toString())
						.withClaim("email", logado.getEmail())
						.withClaim("nome", logado.getNome())
						.sign(algorithm);
		
		return token;
	}
	
	public Optional<Usuario> getUsuario(String token) {
		
		try {
			Usuario usuario = new Usuario();
			
			JWTVerifier verifier = JWT.require(algorithm).withIssuer("SISRES-API").build();
			
			DecodedJWT jwt = verifier.verify(token);
			
			usuario.setId(Long.parseLong(jwt.getSubject()));
			usuario.setEmail(jwt.getClaim("email").asString());
			usuario.setNome(jwt.getClaim("nome").asString());
			
			return Optional.of(usuario);
		} catch (JWTDecodeException e) {
			e.printStackTrace();
			return Optional.empty();
		}
		
	}
}
