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

import br.com.jmsdevel.sisresapi.dto.usuario.UsuarioCadastroFormDto;
import br.com.jmsdevel.sisresapi.dto.usuario.UsuarioDto;
import br.com.jmsdevel.sisresapi.exception.RecursoNaoEncontrado;
import br.com.jmsdevel.sisresapi.interfaces.service.UsuarioInterfaceService;
import br.com.jmsdevel.sisresapi.model.Usuario;
import br.com.jmsdevel.sisresapi.repository.UsuarioRepository;

@Service
@Qualifier("usuario")
public class UsuarioService implements UsuarioInterfaceService<UsuarioDto> {
	
	private static DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
	
	@Autowired
	private UsuarioRepository usuarioRepository;
	
	private Usuario getUsuarioPorId(Long id) {
		return usuarioRepository
				.findById(id)
					.orElseThrow(() -> new RecursoNaoEncontrado("Usuário não encontrado"));
	}

	@Override
	public ResponseEntity<List<UsuarioDto>> todosUsuarios() {
		List<UsuarioDto> usuarios = usuarioRepository
										.findAll()
											.stream()
												.map((u) -> new UsuarioDto(u, formatter))
													.collect(Collectors.toList());
		
		return ResponseEntity.ok(usuarios);
	}

	@Override
	public ResponseEntity<UsuarioDto> usuarioPorId(Long id) {
		
		Usuario usuario = getUsuarioPorId(id);
		
		return ResponseEntity.ok(new UsuarioDto(usuario, formatter));
	}

	@Override
	public ResponseEntity<UsuarioDto> insereUsuario(UsuarioDto usuario, UriComponentsBuilder uriBuilder) {
		Usuario usuarioEntity = new Usuario(usuario, formatter);
		
		if (usuario instanceof UsuarioCadastroFormDto) {
			usuarioEntity.setSenha(((UsuarioCadastroFormDto) usuario).getSenha());
		}
		
		usuarioEntity = usuarioRepository.save(usuarioEntity);
		
		URI uri = uriBuilder.path("/usuarios/{id}").buildAndExpand(usuarioEntity.getId()).toUri();
		
		return ResponseEntity.created(uri).body(new UsuarioDto(usuarioEntity, formatter));
	}

	@Override
	public ResponseEntity<UsuarioDto> atualizaUsuario(Long id, UsuarioDto usuario) {
		Usuario usuarioBanco = getUsuarioPorId(id);
		
		usuarioBanco.setEmail(usuario.getEmail());
		usuarioBanco.setTelefone(usuario.getTelefone());
		
		usuarioBanco = usuarioRepository.save(usuarioBanco);
		
		return ResponseEntity.ok(new UsuarioDto(usuarioBanco, formatter));
	}

	@Override
	public void removeUsuario(Long id) {
		usuarioRepository.deleteById(id);
	}
	
}
