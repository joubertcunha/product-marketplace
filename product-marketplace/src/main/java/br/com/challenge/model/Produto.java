package br.com.challenge.model;

import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Index;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true, callSuper = false)
@Entity
@Table(name = "produto", indexes = { @Index(name = "IDX_PRODUTO_01", columnList = "nome"),
									 @Index(name = "IDX_PRODUTO_02", columnList = "id_categoria_produto")})
public class Produto extends Auditable<String> {

	@EqualsAndHashCode.Include
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_produto")
	private Long id;

	@Column(columnDefinition = "VARCHAR(70)", nullable = false)
	private String nome;

	@Column(columnDefinition = "VARCHAR(150)", nullable = false)
	private String descricao;

	@Column(name = "valor_produto", nullable = false)
	private Double valorProduto;

	@Column
	private Double score;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "data_cadastro", nullable = false)
	private Date dataCadastro;

	@ManyToOne
	@JoinColumn(name = "id_categoria_produto")
	private CategoriaProduto categoriaProduto;

	@OneToMany(mappedBy = "produto", orphanRemoval = true)
	private List<Venda> vendas;
}
