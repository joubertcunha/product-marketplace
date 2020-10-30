package br.com.challenge.model;

import java.time.LocalDateTime;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Entity
@Table(name = "produto")
public class Produto {

	@EqualsAndHashCode.Include
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_produto")
	private Long id;

	@Column
	private String nome;

	@Column
	private String descricao;

	@Column(name = "valor_produto")
	private Double valorProduto;
	
	@Column
	private Integer score;

	@Column(name = "data_insercao")
	private LocalDateTime dataInsercao;

	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "id_categoria_produto")
	private CategoriaProduto categoriaProduto;

	@OneToMany
	private List<Venda> vendas;
}
