package br.com.challenge.controller.dto;

import java.time.LocalDateTime;
import java.util.List;

import lombok.Data;

@Data
public class ErroExceptionDTO {

	private String erro;
	
	private LocalDateTime data;

	private List<String> detalhe;

	public ErroExceptionDTO(String erro, List<String> detalhe) {
		this.erro = erro;
		this.detalhe = detalhe;
		this.data = LocalDateTime.now();
	}

}
