package br.com.jmsdevel.sisresapi.handler;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import br.com.jmsdevel.sisresapi.dto.ErroDto;
import br.com.jmsdevel.sisresapi.exception.RecursoNaoEncontrado;

@ControllerAdvice
public class RecursoNaoEncontradoHandler {
	
	@ExceptionHandler({RecursoNaoEncontrado.class})
	public ResponseEntity<ErroDto> recursoNaoEncontrado(Exception e) {
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ErroDto(e.getMessage()));
	}
}
