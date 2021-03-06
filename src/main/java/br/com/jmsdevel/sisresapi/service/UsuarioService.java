package br.com.jmsdevel.sisresapi.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import br.com.jmsdevel.sisresapi.dto.usuario.AtualizaSenhaUsuarioDto;
import br.com.jmsdevel.sisresapi.dto.usuario.UsuarioCadastroFormDto;
import br.com.jmsdevel.sisresapi.dto.usuario.UsuarioDto;
import br.com.jmsdevel.sisresapi.exception.RecursoNaoEncontrado;
import br.com.jmsdevel.sisresapi.interfaces.service.UsuarioInterfaceService;
import br.com.jmsdevel.sisresapi.mappers.UsuarioMapper;
import br.com.jmsdevel.sisresapi.model.Usuario;
import br.com.jmsdevel.sisresapi.repository.UsuarioRepository;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class UsuarioService implements UsuarioInterfaceService<UsuarioDto> {
	
	private final UsuarioRepository usuarioRepository;
	private final UsuarioMapper usuarioMapper;
	private final BCryptPasswordEncoder encoder;
	
	private Usuario getUsuarioPorId(Long id) {
		return usuarioRepository
				.findById(id)
					.orElseThrow(() -> new RecursoNaoEncontrado("Usuário não encontrado"));
	}

	@Override
	public List<UsuarioDto> todosUsuarios() {
		List<UsuarioDto> usuarios = usuarioRepository
										.findAll()
											.stream()
												.map(usuarioMapper::toDto)
													.collect(Collectors.toList());
		
		return usuarios;
	}

	@Override
	public UsuarioDto usuarioPorId(Long id) {
		
		Usuario usuario = getUsuarioPorId(id);
		
		return usuarioMapper.toDto(usuario);
	}

	@Override
	public UsuarioDto insereUsuario(UsuarioDto usuario) {
		
		if (usuario instanceof UsuarioCadastroFormDto) {
			Usuario usuarioEntity = usuarioMapper.fromDtoCadastroToEntity((UsuarioCadastroFormDto)usuario);
			usuarioEntity.setSenha(encoder.encode(((UsuarioCadastroFormDto) usuario).getSenha()));
			
			usuarioEntity = usuarioRepository.save(usuarioEntity);
			
			return usuarioMapper.toDto(usuarioEntity);
		}
		
		throw new RuntimeException("Erro ao cadastrar usuário");
	}

	@Override
	public UsuarioDto atualizaSenhaUsuario(AtualizaSenhaUsuarioDto atualizaSenhaDto) {
		Usuario usuario = (Usuario) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		
		usuario = getUsuarioPorId(usuario.getId());
		
		if (!encoder.matches(atualizaSenhaDto.getSenhaAntiga(), usuario.getSenha())) {
			throw new RuntimeException("A senha está incorreta");
		}
		
		usuario.setSenha(encoder.encode(atualizaSenhaDto.getSenhaNova()));
		
		return usuarioMapper.toDto(usuarioRepository.save(usuario));
	}	

	@Override
	public void removeUsuario(Long id) {
		usuarioRepository.deleteById(id);
	}

	
}
