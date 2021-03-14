package br.com.jmsdevel.sisresapi.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import br.com.jmsdevel.sisresapi.dto.sala.SalaDto;
import br.com.jmsdevel.sisresapi.exception.RecursoNaoEncontrado;

@SpringBootTest
class SalaServiceTest {
	
	@Autowired
	private SalaService salaService;

	@Test
	public void deveInserirSalaeRecuperarPeloIdInserido() {
		SalaDto salaDto = new SalaDto();
		salaDto.setNome("Sala 01");
		salaDto.setReservavel(true);
		
		salaDto = salaService.insereSala(salaDto);
		
		assertNotNull(salaDto);
		assertNotEquals(0L, salaDto.getId());
		assertEquals("Sala 01", salaDto.getNome());
		assertEquals(true, salaDto.getReservavel());
		
		SalaDto salaDtoNovo = salaService.salaPorId(salaDto.getId());
		
		assertNotNull(salaDtoNovo);
		assertEquals(salaDto, salaDtoNovo);
	}
	
	@Test
	public void deveInserirSalaRecuperareAlterar() {
		SalaDto salaDto = new SalaDto();
		salaDto.setNome("Sala 02");
		salaDto.setReservavel(false);
		
		salaDto = salaService.insereSala(salaDto);
		
		assertNotNull(salaDto);
		assertNotEquals(0L, salaDto.getId());
		assertEquals("Sala 02", salaDto.getNome());
		assertEquals(false, salaDto.getReservavel());
		
		SalaDto salaDtoNovo = salaService.salaPorId(salaDto.getId());
		
		assertNotNull(salaDtoNovo);
		assertEquals(salaDto, salaDtoNovo);
		
		salaDtoNovo.setNome("Sala 03");
		salaDtoNovo.setReservavel(true);
		
		salaDtoNovo = salaService.atualizaSala(salaDtoNovo.getId(), salaDtoNovo);
		
		assertNotNull(salaDtoNovo);
		assertEquals("Sala 03", salaDtoNovo.getNome());
		assertEquals(true, salaDtoNovo.getReservavel());
		
		SalaDto salaConfirmaAlteracao = salaService.salaPorId(salaDtoNovo.getId());
		
		assertNotNull(salaConfirmaAlteracao);
		assertEquals(salaDtoNovo, salaConfirmaAlteracao);
	}
	
	@Test
	public void deveInsereDeletarSala() {
		SalaDto salaDto = new SalaDto();
		salaDto.setNome("Sala 04");
		salaDto.setReservavel(false);
		
		salaDto = salaService.insereSala(salaDto);
		
		assertNotNull(salaDto);
		assertNotEquals(0L, salaDto.getId());
		
		salaService.removeSala(salaDto.getId());
		
		final Long idSala = salaDto.getId();
		
		assertThrows(RecursoNaoEncontrado.class, () -> salaService.salaPorId(idSala));
	}

}
