package br.com.jmsdevel.sisresapi.dto.usuario;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Data;

@Data
public class UsuarioDto {
	
	@JsonIgnore
	private Long id;
	@NotBlank
	private String username;
	@NotBlank
	private String nome;
	@NotBlank
	@NotNull
	@Email
	private String email;
	@Pattern(regexp = "[0-9]{10,11}")
	private String telefone;
	@Pattern(regexp = "([0-2][0-9]|3[0-1])\\/(0[1-9]|1[0-2])\\/[0-9]{4}")
	private String dataNascimento;
	
}
