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

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((nome == null) ? 0 : nome.hashCode());
		result = prime * result + ((reservavel == null) ? 0 : reservavel.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		SalaDto other = (SalaDto) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (nome == null) {
			if (other.nome != null)
				return false;
		} else if (!nome.equals(other.nome))
			return false;
		if (reservavel == null) {
			if (other.reservavel != null)
				return false;
		} else if (!reservavel.equals(other.reservavel))
			return false;
		return true;
	}

	
	
}
