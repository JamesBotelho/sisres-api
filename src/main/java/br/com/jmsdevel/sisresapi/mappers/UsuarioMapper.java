package br.com.jmsdevel.sisresapi.mappers;

import org.mapstruct.Mapper;

import br.com.jmsdevel.sisresapi.dto.usuario.UsuarioCadastroFormDto;
import br.com.jmsdevel.sisresapi.dto.usuario.UsuarioDto;
import br.com.jmsdevel.sisresapi.model.Usuario;

@Mapper(componentModel = "spring")
public interface UsuarioMapper {
	UsuarioDto toDto(Usuario usuario);
	Usuario fromDtoCadastroToEntity(UsuarioCadastroFormDto usuarioCadastroDto);
}
