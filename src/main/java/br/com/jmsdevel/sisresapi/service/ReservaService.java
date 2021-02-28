package br.com.jmsdevel.sisresapi.service;

import java.net.URI;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.util.UriComponentsBuilder;

import br.com.jmsdevel.sisresapi.dto.reserva.ReservaDto;
import br.com.jmsdevel.sisresapi.exception.RecursoNaoEncontrado;
import br.com.jmsdevel.sisresapi.interfaces.service.ReservaInterfaceService;
import br.com.jmsdevel.sisresapi.model.Reserva;
import br.com.jmsdevel.sisresapi.model.Sala;
import br.com.jmsdevel.sisresapi.model.Usuario;
import br.com.jmsdevel.sisresapi.repository.ReservaRepository;
import br.com.jmsdevel.sisresapi.repository.SalaRepository;
import br.com.jmsdevel.sisresapi.repository.UsuarioRepository;

@Service
@Qualifier("reserva")
public class ReservaService implements ReservaInterfaceService<ReservaDto> {
	
	private static final DateTimeFormatter formatterDate = DateTimeFormatter.ofPattern("dd/MM/yyyy");
	private static final DateTimeFormatter formatterHour = DateTimeFormatter.ofPattern("HH:mm:ss");
	private static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");
	
	@Autowired
	private ReservaRepository reservaRepository;
	
	@Autowired
	private UsuarioRepository usuarioRepository;
	
	@Autowired
	private SalaRepository salaRepository;
	
	private Reserva getPorId(Long id) {
		return reservaRepository.findById(id).orElseThrow(() -> new RecursoNaoEncontrado("Reserva não encontrada"));
	}

	@Override
	public ResponseEntity<List<ReservaDto>> todasAsReservas() {
		List<ReservaDto> reservas = reservaRepository
										.findAll()
											.stream()
												.map((r) -> new ReservaDto(r, formatterDate, formatterHour))
													.collect(Collectors.toList());
		return ResponseEntity.ok(reservas);
	}

	@Override
	public ResponseEntity<ReservaDto> reservaPorId(Long id) {
		Reserva reserva = getPorId(id);
		return ResponseEntity.ok(new ReservaDto(reserva, formatterDate, formatterHour));
	}

	@Override
	public ResponseEntity<ReservaDto> insereReserva(ReservaDto reserva, UriComponentsBuilder uriBuilder) {
		
		Usuario usuario = usuarioRepository.findById(1L).orElseThrow(() -> new RecursoNaoEncontrado("Usuário não encontrado"));
		
		Sala sala = salaRepository.findById(reserva.getIdSala()).orElseThrow(() -> new RecursoNaoEncontrado("Sala não encontrada"));
		
		Reserva reservaEntity = new Reserva(reserva, usuario, sala, formatter);
		
		reservaEntity = reservaRepository.save(reservaEntity);
		
		URI uri = uriBuilder.path("/reserva/{id}").buildAndExpand(reservaEntity.getId()).toUri();
		
		return ResponseEntity.created(uri).body(new ReservaDto(reservaEntity, formatterDate, formatterHour));
	}

	@Override
	public void removeReserva(Long id) {
		reservaRepository.deleteById(id);
	}
	
}
