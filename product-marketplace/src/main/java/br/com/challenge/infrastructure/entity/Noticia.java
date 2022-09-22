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
@Table(name = "noticia", indexes = {@Index(name = "IDX_NOTICIA_01", columnList = "id_categoria_produto, data_publicada")})
public class Noticia {

	@EqualsAndHashCode.Include
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_noticia")
	private Long id;

	@Column(columnDefinition = "VARCHAR(70)")
	private String autor;

	@Column(columnDefinition = "VARCHAR(100)")
	private String titulo;

	@Column(columnDefinition = "VARCHAR(150)")
	private String descricao;

	@Column(columnDefinition = "VARCHAR(150)")
	private String url;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "data_publicada")
	private Date dataPublicada;

	@Column(columnDefinition = "VARCHAR(255)")
	private String conteudo;

	@ManyToOne
	@JoinColumn(name = "id_categoria_produto")
	private CategoriaProduto categoriaProduto;
}
