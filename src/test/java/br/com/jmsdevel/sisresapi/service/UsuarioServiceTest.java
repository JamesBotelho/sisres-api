package br.com.jmsdevel.sisresapi.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import br.com.jmsdevel.sisresapi.dto.usuario.UsuarioCadastroFormDto;
import br.com.jmsdevel.sisresapi.dto.usuario.UsuarioDto;
import br.com.jmsdevel.sisresapi.exception.RecursoNaoEncontrado;

@SpringBootTest
class UsuarioServiceTest {
	
	@Autowired
	private UsuarioService usuarioService;
	
	@Test
	public void deveInserirUmUsuarioNoBanco() {
		UsuarioCadastroFormDto usuarioDto = new UsuarioCadastroFormDto();
		usuarioDto.setUsername("james_");
		usuarioDto.setNome("James");
		usuarioDto.setEmail("james_oliveira@live.com");
		usuarioDto.setDataNascimento("1995-07-26");
		usuarioDto.setSenha("1234567890");
		usuarioDto.setTelefone("83999154081");
		
		UsuarioDto retornoUsuario = usuarioService.insereUsuario(usuarioDto);
		assertNotNull(retornoUsuario);
		assertEquals("james_", retornoUsuario.getUsername());
		assertEquals("James", retornoUsuario.getNome());
		assertEquals("james_oliveira@live.com", retornoUsuario.getEmail());
		assertEquals("1995-07-26", retornoUsuario.getDataNascimento());
		assertEquals(2L, retornoUsuario.getId());
	}
	
	@Test
	public void deveDeletarUsuarioComIdUmNoBanco() {
		usuarioService.removeUsuario(1L);
		
		assertThrows(RecursoNaoEncontrado.class, () -> usuarioService.usuarioPorId(1L));
	}
}
