package br.com.jmsdevel.sisresapi.interfaces.service;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.util.UriComponentsBuilder;

public interface ReservaInterfaceService<T> {
	ResponseEntity<List<T>> todasAsReservas();
	ResponseEntity<T> reservaPorId(Long id);
	ResponseEntity<T> insereReserva(T reserva, UriComponentsBuilder uriBuilder);
	void removeReserva(Long id);
}
