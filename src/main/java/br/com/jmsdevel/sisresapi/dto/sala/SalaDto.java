package br.com.jmsdevel.sisresapi.dto.sala;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Data;

@Data
public class SalaDto {
	@JsonIgnore
	private Long id;
	@NotBlank
	private String nome;
	@NotNull
	private Boolean reservavel;
	
}
