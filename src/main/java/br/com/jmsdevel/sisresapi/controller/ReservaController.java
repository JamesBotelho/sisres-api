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
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import br.com.jmsdevel.sisresapi.dto.reserva.ReservaDto;
import br.com.jmsdevel.sisresapi.interfaces.service.ReservaInterfaceService;

@RestController
@RequestMapping("/reserva")
public class ReservaController {
	
	@Autowired
	@Qualifier("reserva")
	private ReservaInterfaceService<ReservaDto> reservaService;
	
	@GetMapping
	public ResponseEntity<List<ReservaDto>> listaReservas() {
		return reservaService.todasAsReservas();
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<ReservaDto> reservaPorId(@PathVariable Long id) {
		return reservaService.reservaPorId(id);
	}
	
	@PostMapping
	public ResponseEntity<ReservaDto> insereReserva(@Valid @RequestBody ReservaDto reservaDto, UriComponentsBuilder uriBuilder) {
		return reservaService.insereReserva(reservaDto, uriBuilder);
	}
	
	@DeleteMapping("/{id}")
	public void deletaReserva(@PathVariable Long id) {
		reservaService.removeReserva(id);
	}
}
