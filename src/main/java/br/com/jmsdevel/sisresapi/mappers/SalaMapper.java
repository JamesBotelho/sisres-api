package br.com.jmsdevel.sisresapi.mappers;

import org.mapstruct.Mapper;

import br.com.jmsdevel.sisresapi.dto.sala.SalaDto;
import br.com.jmsdevel.sisresapi.model.Sala;

@Mapper(componentModel = "spring")
public interface SalaMapper {
	SalaDto toDto(Sala sala);
	Sala toEntity(SalaDto salaDto);
}
