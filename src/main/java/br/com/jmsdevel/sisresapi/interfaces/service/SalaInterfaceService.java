package br.com.jmsdevel.sisresapi.interfaces.service;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.util.UriComponentsBuilder;

public interface SalaInterfaceService<T> {
	ResponseEntity<List<T>> todasAsSalas();
	ResponseEntity<T> salaPorId(Long id);
	ResponseEntity<T> insereSala(T sala, UriComponentsBuilder uriBuilder);
	ResponseEntity<T> atualizaSala(Long id, T sala);
	void removeSala(Long id);
}
