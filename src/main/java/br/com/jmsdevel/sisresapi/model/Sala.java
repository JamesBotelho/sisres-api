package br.com.jmsdevel.sisresapi.model;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import br.com.jmsdevel.sisresapi.dto.sala.SalaDto;

@Entity
public class Sala {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@Column(nullable = false, unique = true, length = 50)
	private String nome;
	@Column(nullable = false)
	private Boolean reservavel;
	@OneToMany(mappedBy = "sala")
	private List<Reserva> reservas;
	
	public Sala() {}
	
	public Sala(SalaDto salaDto) {
		this.nome = salaDto.getNome();
		this.reservavel = salaDto.getReservavel();
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

	public List<Reserva> getReservas() {
		return reservas;
	}

	public void setReservas(List<Reserva> reservas) {
		this.reservas = reservas;
	}
	
	
}
