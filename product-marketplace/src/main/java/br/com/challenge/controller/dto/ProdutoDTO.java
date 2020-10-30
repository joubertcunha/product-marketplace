package br.com.challenge.controller.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class ProdutoDTO {

	private Long id;

	private String nome;

	private String descricao;

	private CategoriaProdutoDTO categoriaProduto;

}
