package br.com.challenge.controller.dto;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@ApiModel(description = "Informações relacionadas ao produto.")
public class ProdutoDTO {

	private Long id;

	@NotBlank(message = "O campo nome deve ser preenchido.")
	@Size(max = 70, message = "O campo nome tem valor máximo de 70 caracteres.")
	@ApiModelProperty(notes = "Nome do produto.", example = "Bola", required = true)
	private String nome;

	@NotBlank(message = "O campo descrição deve ser preenchido.")
	@Size(max = 150, message = "O descricao nome tem valor máximo de 70 caracteres.")
	@ApiModelProperty(notes = "Descrição do produto.", example = "Bola de futebol", required = true)
	private String descricao;
	
    @NotNull(message = "O campo valor do Produto deve ser preenchido.")
	@ApiModelProperty(notes = "Valor do produto.", example = "10.00", required = true)
	private Double valorProduto;

	private CategoriaProdutoDTO categoriaProduto;

}
