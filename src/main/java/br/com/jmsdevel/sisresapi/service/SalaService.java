package br.com.jmsdevel.sisresapi.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import br.com.jmsdevel.sisresapi.dto.sala.SalaDto;
import br.com.jmsdevel.sisresapi.exception.RecursoNaoEncontrado;
import br.com.jmsdevel.sisresapi.interfaces.service.SalaInterfaceService;
import br.com.jmsdevel.sisresapi.mappers.SalaMapper;
import br.com.jmsdevel.sisresapi.model.Sala;
import br.com.jmsdevel.sisresapi.repository.SalaRepository;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class SalaService implements SalaInterfaceService<SalaDto> {
	
	private final SalaRepository salaRepository;
	private final SalaMapper salaMapper;
	
	private Sala getPorId(Long id) {
		return salaRepository.findById(id).orElseThrow(() -> new RecursoNaoEncontrado("Sala não encontrada"));
	}

	@Override
	public List<SalaDto> todasAsSalas() {
		List<SalaDto> salas = salaRepository
								.findAll()
									.stream()
										.map(salaMapper::toDto)
											.collect(Collectors.toList());
		return salas;
	}

	@Override
	public SalaDto salaPorId(Long id) {
		Sala s = getPorId(id);
		return salaMapper.toDto(s);
	}

	@Override
	public SalaDto insereSala(SalaDto sala) {
		
		Sala salaEntity = salaMapper.toEntity(sala);
		
		salaEntity = salaRepository.save(salaEntity);
		
		return salaMapper.toDto(salaEntity);
	}

	@Override
	public SalaDto atualizaSala(Long id, SalaDto sala) {
		Sala salaBanco = getPorId(id);
		
		salaBanco.setNome(sala.getNome());
		salaBanco.setReservavel(sala.getReservavel());
		
		salaRepository.save(salaBanco);
		
		return salaMapper.toDto(salaBanco);
	}

	@Override
	public void removeSala(Long id) {
		salaRepository.deleteById(id);
	}
	
}
