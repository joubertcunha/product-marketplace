package br.com.challenge.controller.dto;

import java.time.LocalDateTime;
import java.util.List;

import lombok.Data;

@Data
public class ErroExceptionDTO {

	private int status;

	private String mensagem;

	private LocalDateTime data;

	private List<String> detalhe;

	public ErroExceptionDTO(int status, String mensagem, List<String> detalhe) {
		this.status = status;
		this.mensagem = mensagem;
		this.detalhe = detalhe;
		this.data = LocalDateTime.now();
	}

}
