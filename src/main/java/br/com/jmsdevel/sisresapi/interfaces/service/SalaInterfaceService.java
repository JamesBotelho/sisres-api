package br.com.jmsdevel.sisresapi.interfaces.service;

import java.util.List;

public interface SalaInterfaceService<T> {
	List<T> todasAsSalas();
	T salaPorId(Long id);
	T insereSala(T sala);
	T atualizaSala(Long id, T sala);
	void removeSala(Long id);
}
