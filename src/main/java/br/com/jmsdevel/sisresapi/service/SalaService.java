package br.com.jmsdevel.sisresapi.service;

import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.util.UriComponentsBuilder;

import br.com.jmsdevel.sisresapi.dto.sala.SalaDto;
import br.com.jmsdevel.sisresapi.exception.RecursoNaoEncontrado;
import br.com.jmsdevel.sisresapi.interfaces.service.SalaInterfaceService;
import br.com.jmsdevel.sisresapi.model.Sala;
import br.com.jmsdevel.sisresapi.repository.SalaRepository;

@Service
@Qualifier("sala")
public class SalaService implements SalaInterfaceService<SalaDto> {
	
	@Autowired
	private SalaRepository salaRepository;
	
	private Sala getPorId(Long id) {
		return salaRepository.findById(id).orElseThrow(() -> new RecursoNaoEncontrado("Sala não encontrada"));
	}

	@Override
	public ResponseEntity<List<SalaDto>> todasAsSalas() {
		List<SalaDto> salas = salaRepository
								.findAll()
									.stream()
										.map((s) -> new SalaDto(s))
											.collect(Collectors.toList());
		return ResponseEntity.ok(salas);
	}

	@Override
	public ResponseEntity<SalaDto> salaPorId(Long id) {
		Sala s = getPorId(id);
		return ResponseEntity.ok(new SalaDto(s));
	}

	@Override
	public ResponseEntity<SalaDto> insereSala(SalaDto sala, UriComponentsBuilder uriBuilder) {
		
		Sala salaEntity = new Sala(sala);
		
		salaEntity = salaRepository.save(salaEntity);
		
		URI uri = uriBuilder.path("/sala/{id}").buildAndExpand(salaEntity.getId()).toUri();
		
		return ResponseEntity.created(uri).body(new SalaDto(salaEntity));
	}

	@Override
	public ResponseEntity<SalaDto> atualizaSala(Long id, SalaDto sala) {
		Sala salaBanco = getPorId(id);
		
		salaBanco.setNome(sala.getNome());
		salaBanco.setReservavel(sala.getReservavel());
		
		salaRepository.save(salaBanco);
		
		return ResponseEntity.ok(new SalaDto(salaBanco));
	}

	@Override
	public void removeSala(Long id) {
		salaRepository.deleteById(id);
	}
	
}
