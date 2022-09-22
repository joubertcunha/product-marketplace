package br.com.challenge.infrastructure.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Index;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Entity
@Table(name = "venda", indexes = { @Index(name = "IDX_VENDA_01", columnList = "id_produto"),
								   @Index(name = "IDX_VENDA_02", columnList = "data_venda")})
public class Venda {

	@EqualsAndHashCode.Include
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_venda")
	private Long id;

	@Column
	private Integer avaliacao;

	@Column
	private Integer quantidade;

	@Column(name = "valor_total")
	private Double valorTotal;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "data_venda")
	private Date dataVenda;

	@ManyToOne
	@JoinColumn(name = "id_produto")
	private Produto produto;

	@ManyToOne
	@JoinColumn(name = "id_vendedor")
	private Vendedor vendedor;

	@ManyToOne
	@JoinColumn(name = "id_comprador")
	private Comprador comprador;

}
