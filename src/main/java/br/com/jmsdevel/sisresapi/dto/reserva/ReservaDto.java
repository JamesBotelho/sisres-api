package br.com.jmsdevel.sisresapi.dto.reserva;

import java.time.format.DateTimeFormatter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Positive;

import br.com.jmsdevel.sisresapi.model.Reserva;

public class ReservaDto {
	
	private Long id;
	
	@NotBlank
	@Pattern(regexp = "([0-2][0-9]|3[0-1])\\/(0[1-9]|1[0-2])\\/[0-9]{4}")
	private String data;
	
	@NotBlank
	@Pattern(regexp = "([0][0-9]|1[0-9]|2[0-3])\\:([0-5][0-9])\\:([0-5][0-9])")
	private String horaInicio;
	@Pattern(regexp = "([0][0-9]|1[0-9]|2[0-3])\\:([0-5][0-9])\\:([0-5][0-9])")
	private String horaFim;
	
	@Positive
	@NotNull
	private Long idSala;
	
	public ReservaDto() {}

	public ReservaDto(Long id, String data, String horaInicio, String horaFim, Long idSala) {
		this.id = id;
		this.data = data;
		this.horaInicio = horaInicio;
		this.horaFim = horaFim;
		this.idSala = idSala;
	}
	
	public ReservaDto(Reserva reserva, DateTimeFormatter formatterDate, DateTimeFormatter formatterHour) {
		this.id = reserva.getId();
		this.data = formatterDate.format(reserva.getInicio());
		this.horaInicio = formatterHour.format(reserva.getInicio());
		this.horaFim = formatterHour.format(reserva.getFim());
		this.idSala = reserva.getSala().getId();
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getData() {
		return data;
	}

	public void setData(String data) {
		this.data = data;
	}

	public String getHoraInicio() {
		return horaInicio;
	}

	public void setHoraInicio(String horaInicio) {
		this.horaInicio = horaInicio;
	}

	public String getHoraFim() {
		return horaFim;
	}

	public void setHoraFim(String horaFim) {
		this.horaFim = horaFim;
	}

	public Long getIdSala() {
		return idSala;
	}

	public void setIdSala(Long idSala) {
		this.idSala = idSala;
	}
	
	
}
