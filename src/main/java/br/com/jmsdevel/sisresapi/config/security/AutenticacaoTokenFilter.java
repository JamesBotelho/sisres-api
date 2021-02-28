package br.com.jmsdevel.sisresapi.config.security;

import java.io.IOException;
import java.util.Optional;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import br.com.jmsdevel.sisresapi.model.Usuario;
import br.com.jmsdevel.sisresapi.service.TokenService;

public class AutenticacaoTokenFilter extends OncePerRequestFilter {
	
	private TokenService tokenService;

	public AutenticacaoTokenFilter(TokenService tokenService) {
		this.tokenService = tokenService;
	}
	
	private Optional<String> recuperaToken(HttpServletRequest request) {
		String token = request.getHeader("Authorization");
		
		if (token == null || token.isBlank() || !token.startsWith("Bearer ")) {
			return Optional.empty();
		}
		
		return Optional.of(token.replace("Bearer ", ""));
	}
	
	private void autenticarUsuario(Usuario usuario) {
		UsernamePasswordAuthenticationToken auth = new UsernamePasswordAuthenticationToken(usuario, null, usuario.getAuthorities());
		SecurityContextHolder.getContext().setAuthentication(auth);
	}

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		
		Optional<String> token = recuperaToken(request);
		
		if (token.isPresent()) {
			Optional<Usuario> usuarioOpt = tokenService.getUsuario(token.get());
			if (usuarioOpt.isPresent()) {
				autenticarUsuario(usuarioOpt.get());
			}
		}
		
		filterChain.doFilter(request, response);
	}
	
}
