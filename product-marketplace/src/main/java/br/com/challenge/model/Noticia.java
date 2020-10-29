package br.com.challenge.model;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Entity
@Table(name = "noticia")
public class Noticia {

	@EqualsAndHashCode.Include
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_noticia")
	private Long id;
	
	@Column
	private String author;
	
	@Column
	private String title;
	
	@Column
	private String description;
	
	@Column
	private String url;
	
	@Column
	private LocalDateTime publishedAt;
	
	@Column
	private String content;
	
	@ManyToOne
	@JoinColumn(name = "id_categoria_produto")
	private CategoriaProduto categoriaProduto;
}
