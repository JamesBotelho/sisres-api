package br.com.jmsdevel.sisresapi.dto;

import java.time.format.DateTimeFormatter;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

import br.com.jmsdevel.sisresapi.model.Usuario;

public class UsuarioDto {
	
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
	
	public UsuarioDto() {}
	
	public UsuarioDto(Usuario u, DateTimeFormatter formatter) {
		this.id = u.getId();
		this.username = u.getUsername();
		this.nome = u.getNome();
		this.email = u.getEmail();
		this.telefone = u.getTelefone();
		this.dataNascimento = formatter.format(u.getDataNascimento());
	}

	public UsuarioDto(Long id, String username, String email, String telefone, String dataNascimento) {
		this.id = id;
		this.username = username;
		this.email = email;
		this.telefone = telefone;
		this.dataNascimento = dataNascimento;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getTelefone() {
		return telefone;
	}

	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}

	public String getDataNascimento() {
		return dataNascimento;
	}

	public void setDataNascimento(String dataNascimento) {
		this.dataNascimento = dataNascimento;
	}
	
}
