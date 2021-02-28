package br.com.jmsdevel.sisresapi.dto.usuario;

import javax.validation.constraints.NotBlank;

public class UsuarioCadastroFormDto extends UsuarioDto {
	
	@NotBlank
	private String senha;

	public UsuarioCadastroFormDto(Long id, String username, String email, String telefone, String dataNascimento, String senha) {
		super(id, username, email, telefone, dataNascimento);
		this.senha = senha;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}
	
}
