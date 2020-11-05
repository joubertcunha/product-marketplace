package br.com.challenge.controller.dto;

import javax.validation.constraints.NotBlank;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class ProdutoDTO {

	private Long id;

	@NotBlank(message = "o campo nome deve ser preenchido.")
	private String nome;

	@NotBlank(message = "o campo descrição deve ser preenchido.")
	private String descricao;
	
	private Double valorProduto;

	private CategoriaProdutoDTO categoriaProduto;

}
