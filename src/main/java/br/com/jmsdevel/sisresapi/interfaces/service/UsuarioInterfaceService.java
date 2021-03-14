package br.com.jmsdevel.sisresapi.interfaces.service;

import java.util.List;

public interface UsuarioInterfaceService<T> {
	List<T> todosUsuarios();
	T usuarioPorId(Long id);
	T insereUsuario(T usuario);
	T atualizaUsuario(Long id, T usuario);
	void removeUsuario(Long id);
}
