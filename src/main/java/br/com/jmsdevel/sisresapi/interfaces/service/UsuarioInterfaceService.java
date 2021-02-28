package br.com.jmsdevel.sisresapi.interfaces.service;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.util.UriComponentsBuilder;

public interface UsuarioInterfaceService<T> {
	ResponseEntity<List<T>> todosUsuarios();
	ResponseEntity<T> usuarioPorId(Long id);
	ResponseEntity<T> insereUsuario(T usuario, UriComponentsBuilder uriBuilder);
	ResponseEntity<T> atualizaUsuario(Long id, T usuario);
	void removeUsuario(Long id);
}
