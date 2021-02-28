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

import br.com.jmsdevel.sisresapi.dto.UsuarioCadastroFormDto;
import br.com.jmsdevel.sisresapi.dto.UsuarioDto;
import br.com.jmsdevel.sisresapi.interfaces.service.UsuarioInterfaceService;

@RestController
@RequestMapping("/usuario")
public class UsuarioController {
	
	@Autowired
	@Qualifier("usuario")
	private UsuarioInterfaceService<UsuarioDto> usuarioService;
	
	@GetMapping
	public ResponseEntity<List<UsuarioDto>> listaTodosUsuarios() {
		return usuarioService.todosUsuarios();
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<UsuarioDto> recuperaUsuarioPorId(@PathVariable Long id) {
		return usuarioService.usuarioPorId(id);
	}
	
	@PostMapping
	public ResponseEntity<UsuarioDto> insereUsuario(@Valid @RequestBody UsuarioCadastroFormDto usuario, UriComponentsBuilder uriBuilder) {
		return usuarioService.insereUsuario(usuario, uriBuilder);
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<UsuarioDto> atualizaUsuario(@PathVariable Long id, @Valid @RequestBody UsuarioDto usuario) {
		return usuarioService.atualizaUsuario(id, usuario);
	}
	
	@DeleteMapping("/{id}")
	public void deletaUsuario(@PathVariable Long id) {
		usuarioService.removeUsuario(id);
	}
}
