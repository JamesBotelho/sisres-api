package br.com.jmsdevel.sisresapi.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import br.com.jmsdevel.sisresapi.dto.sala.SalaDto;
import br.com.jmsdevel.sisresapi.interfaces.service.SalaInterfaceService;

@RestController
@RequestMapping("/sala")
public class SalaController {
	
	@Autowired
	@Qualifier("sala")
	private SalaInterfaceService<SalaDto> salaService;
	
	@GetMapping
	public ResponseEntity<List<SalaDto>> buscaTodasSalas() {
		return salaService.todasAsSalas();
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<SalaDto> buscaSalaPorId(@PathVariable Long id) {
		return salaService.salaPorId(id);
	}
	
	@PostMapping
	public ResponseEntity<SalaDto> insereSala(@Valid @RequestBody SalaDto salaDto, UriComponentsBuilder uriBuilder) {
		return salaService.insereSala(salaDto, uriBuilder);
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<SalaDto> atualizaSala(@PathVariable Long id, @Valid @RequestBody SalaDto salaDto) {
		return salaService.atualizaSala(id, salaDto);
	}
	
	@DeleteMapping("/{id}")
	public void deletaSala(@PathVariable Long id) {
		salaService.removeSala(id);
	}
}
