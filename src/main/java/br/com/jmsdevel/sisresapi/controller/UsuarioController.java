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

import br.com.jmsdevel.sisresapi.dto.usuario.AtualizaSenhaUsuarioDto;
import br.com.jmsdevel.sisresapi.dto.usuario.UsuarioCadastroFormDto;
import br.com.jmsdevel.sisresapi.dto.usuario.UsuarioDto;
import br.com.jmsdevel.sisresapi.interfaces.service.UsuarioInterfaceService;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@RestController
@RequestMapping("/usuario")
public class UsuarioController {
	
	private final UsuarioInterfaceService<UsuarioDto> usuarioService;
	
	@GetMapping
	public ResponseEntity<List<UsuarioDto>> listaTodosUsuarios() {
		return ResponseEntity.ok(usuarioService.todosUsuarios());
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<UsuarioDto> recuperaUsuarioPorId(@PathVariable Long id) {
		return ResponseEntity.ok(usuarioService.usuarioPorId(id));
	}
	
	@PostMapping
	public ResponseEntity<UsuarioDto> insereUsuario(@Valid @RequestBody UsuarioCadastroFormDto usuario, UriComponentsBuilder uriBuilder) {
		
		UsuarioDto usuarioInserido = usuarioService.insereUsuario(usuario);
		
		URI uri = uriBuilder.path("/usuario/{id}").buildAndExpand(usuarioInserido.getId()).toUri();
		
		return ResponseEntity.created(uri).body(usuarioInserido);
	}
	
	@PutMapping("/mudarsenha")
	public ResponseEntity<UsuarioDto> atualizaUsuario(@Valid @RequestBody AtualizaSenhaUsuarioDto usuario) {
		return ResponseEntity.ok(usuarioService.atualizaSenhaUsuario(usuario));
	}
	
	@DeleteMapping("/{id}")
	public void deletaUsuario(@PathVariable Long id) {
		usuarioService.removeUsuario(id);
	}
}
