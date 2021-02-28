package br.com.jmsdevel.sisresapi.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.jmsdevel.sisresapi.dto.LoginFormDto;
import br.com.jmsdevel.sisresapi.dto.TokenDto;
import br.com.jmsdevel.sisresapi.service.TokenService;

@RestController
@RequestMapping("/auth")
public class AutenticacaoController {
	
	@Autowired
	private AuthenticationManager authManager;
	
	@Autowired
	private TokenService tokenService;
	
	@PostMapping
	private ResponseEntity<TokenDto> autenticar(@RequestBody LoginFormDto loginFormDto) {
		UsernamePasswordAuthenticationToken login = new UsernamePasswordAuthenticationToken(loginFormDto.getUsername(), loginFormDto.getSenha());
		
		try {
			Authentication authentication = authManager.authenticate(login);
			String token = tokenService.geraToken(authentication);
			
			return ResponseEntity.ok(new TokenDto(token, "Bearer"));
		} catch (AuthenticationException e) {
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
		}
	}
}
