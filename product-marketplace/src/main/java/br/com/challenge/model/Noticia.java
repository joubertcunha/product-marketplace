package br.com.challenge.model;

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
@Table(name = "noticia", indexes = {@Index(name = "IDX_NOTICIA_01", columnList = "id_categoria_produto, data_publicada")})
public class Noticia {

	@EqualsAndHashCode.Include
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_noticia")
	private Long id;

	@Column
	private String autor;

	@Column
	private String titulo;

	@Column
	private String descricao;

	@Column
	private String url;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "data_publicada")
	private Date dataPublicada;

	@Column
	private String conteudo;

	@ManyToOne
	@JoinColumn(name = "id_categoria_produto")
	private CategoriaProduto categoriaProduto;
}
