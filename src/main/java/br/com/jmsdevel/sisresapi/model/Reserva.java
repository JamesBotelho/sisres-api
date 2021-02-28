package br.com.jmsdevel.sisresapi.model;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import br.com.jmsdevel.sisresapi.dto.reserva.ReservaDto;

@Entity
public class Reserva {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private LocalDateTime inicio;
	private LocalDateTime fim;
	@ManyToOne
	@JoinColumn(name = "id_sala")
	private Sala sala;
	@ManyToOne
	@JoinColumn(name = "id_usuario")
	private Usuario usuario;
	
	public Reserva() {}
	
	public Reserva(ReservaDto reservaDto, Usuario usuario, Sala sala, DateTimeFormatter formatter) {
		this.inicio = LocalDateTime.parse(reservaDto.getData() + " " + reservaDto.getHoraInicio(), formatter);
		this.fim = LocalDateTime.parse(reservaDto.getData() + " " + reservaDto.getHoraFim(), formatter);
		this.sala = sala;
		this.usuario = usuario;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public LocalDateTime getInicio() {
		return inicio;
	}

	public void setInicio(LocalDateTime inicio) {
		this.inicio = inicio;
	}

	public LocalDateTime getFim() {
		return fim;
	}

	public void setFim(LocalDateTime fim) {
		this.fim = fim;
	}

	public Sala getSala() {
		return sala;
	}

	public void setSala(Sala sala) {
		this.sala = sala;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}
	
	
}
