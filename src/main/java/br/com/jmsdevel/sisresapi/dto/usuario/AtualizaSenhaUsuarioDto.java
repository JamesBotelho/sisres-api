package br.com.jmsdevel.sisresapi.dto.usuario;

import org.hibernate.validator.constraints.Length;

import lombok.Data;

@Data
public class AtualizaSenhaUsuarioDto {
	@Length(min = 10, max = 20)
	private String senhaAntiga;
	@Length(min = 10, max = 20)
	private String senhaNova;
}
