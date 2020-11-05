package br.com.challenge.repository;

import java.util.Date;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import br.com.challenge.model.Noticia;

@Repository
public interface NoticiaRepository extends JpaRepository<Noticia, Long>{

	@Query("SELECT count(n) FROM Noticia n WHERE n.categoriaProduto.id = :idCategoria AND dataPublicada BETWEEN :dataInicio AND :dataFim")
	Integer obterQuantidadeNoticiaCategoria(@Param("idCategoria") Long idCategoria, @Param("dataInicio") Date dataInicio, @Param("dataFim") Date dataFim);
}
