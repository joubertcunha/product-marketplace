package br.com.challenge.controller.dto;

import java.util.Date;

import lombok.Data;

@Data
public class ProdutoRanqueadoDTO {

	private Long id;
	private String nome;
	private String descricao;
	private Date dataCadastro;
	private Double score;

	private String termoPesquisado;
	private Date dataAtual;
}
