package br.com.jmsdevel.sisresapi.interfaces.service;

import java.util.List;

import br.com.jmsdevel.sisresapi.dto.usuario.AtualizaSenhaUsuarioDto;

public interface UsuarioInterfaceService<T> {
	List<T> todosUsuarios();
	T usuarioPorId(Long id);
	T insereUsuario(T usuario);
	T atualizaSenhaUsuario(AtualizaSenhaUsuarioDto atualizaSenhaDto);
	void removeUsuario(Long id);
}
