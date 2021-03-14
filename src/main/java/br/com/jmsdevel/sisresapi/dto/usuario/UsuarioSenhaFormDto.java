package br.com.jmsdevel.sisresapi.dto.usuario;

import javax.validation.constraints.NotBlank;

import lombok.Data;

@Data
public class UsuarioSenhaFormDto extends UsuarioDto {
	
	@NotBlank
	private String senhaAntiga;
	@NotBlank
	private String novaSenha;
	
}
