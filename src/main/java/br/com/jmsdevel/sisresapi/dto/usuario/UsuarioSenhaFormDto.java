package br.com.jmsdevel.sisresapi.dto.usuario;

import javax.validation.constraints.NotBlank;

public class UsuarioSenhaFormDto extends UsuarioDto {
	
	@NotBlank
	private String senhaAntiga;
	@NotBlank
	private String novaSenha;
	
	public UsuarioSenhaFormDto() {
	}

	public UsuarioSenhaFormDto(String senhaAntiga, String novaSenha) {
		this.senhaAntiga = senhaAntiga;
		this.novaSenha = novaSenha;
	}

	public String getSenhaAntiga() {
		return senhaAntiga;
	}

	public void setSenhaAntiga(String senhaAntiga) {
		this.senhaAntiga = senhaAntiga;
	}

	public String getNovaSenha() {
		return novaSenha;
	}

	public void setNovaSenha(String novaSenha) {
		this.novaSenha = novaSenha;
	}
	
	
}
