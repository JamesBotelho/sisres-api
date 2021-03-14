package br.com.jmsdevel.sisresapi.controller;

import java.net.URI;
import java.util.List;

import javax.validation.Valid;

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
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@RestController
@RequestMapping("/sala")
public class SalaController {
	
	private final SalaInterfaceService<SalaDto> salaService;
	
	@GetMapping
	public ResponseEntity<List<SalaDto>> buscaTodasSalas() {
		return ResponseEntity.ok(salaService.todasAsSalas());
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<SalaDto> buscaSalaPorId(@PathVariable Long id) {
		return ResponseEntity.ok(salaService.salaPorId(id));
	}
	
	@PostMapping
	public ResponseEntity<SalaDto> insereSala(@Valid @RequestBody SalaDto salaDto, UriComponentsBuilder uriBuilder) {
		
		SalaDto retorno = salaService.insereSala(salaDto);
		
		URI uri = uriBuilder.path("sala/{id}").buildAndExpand("id", retorno.getId()).toUri();
		
		return ResponseEntity.created(uri).body(salaService.insereSala(salaDto));
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<SalaDto> atualizaSala(@PathVariable Long id, @Valid @RequestBody SalaDto salaDto) {
		return ResponseEntity.ok(salaService.atualizaSala(id, salaDto));
	}
	
	@DeleteMapping("/{id}")
	public void deletaSala(@PathVariable Long id) {
		salaService.removeSala(id);
	}
}
