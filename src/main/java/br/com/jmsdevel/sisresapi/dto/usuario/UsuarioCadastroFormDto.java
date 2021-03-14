package br.com.jmsdevel.sisresapi.dto.usuario;

import javax.validation.constraints.NotBlank;

import lombok.Data;

@Data
public class UsuarioCadastroFormDto extends UsuarioDto {
	
	@NotBlank
	private String senha;
	
}
