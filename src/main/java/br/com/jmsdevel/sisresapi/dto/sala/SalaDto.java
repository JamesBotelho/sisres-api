package br.com.jmsdevel.sisresapi.dto.sala;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import br.com.jmsdevel.sisresapi.model.Sala;

public class SalaDto {
	private Long id;
	@NotBlank
	private String nome;
	@NotNull
	private Boolean reservavel;
	
	public SalaDto() {}

	public SalaDto(Long id, String nome, Boolean reservavel) {
		this.id = id;
		this.nome = nome;
		this.reservavel = reservavel;
	}
	
	public SalaDto(Sala sala) {
		this.id = sala.getId();
		this.nome = sala.getNome();
		this.reservavel = sala.getReservavel();
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public Boolean getReservavel() {
		return reservavel;
	}

	public void setReservavel(Boolean reservavel) {
		this.reservavel = reservavel;
	}
	
	
}
