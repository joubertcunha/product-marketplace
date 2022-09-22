package br.com.challenge.infrastructure.repository;

import java.util.Date;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import br.com.challenge.infrastructure.entity.Venda;
import feign.Param;

@Repository
public interface VendaRepository extends JpaRepository<Venda, Long> {

	@Query("SELECT AVG(v.avaliacao) FROM Venda v WHERE v.produto.id = :idProduto AND v.dataVenda BETWEEN :dataInicio AND :dataFim")
	Double obterMediaAvaliacaoProduto(@Param("idProduto") Long idProduto, @Param("dataInicio") Date dataInicio, @Param("dataFim") Date dataFim);
	
	@Query("SELECT count(v) FROM Venda v where v.produto.id = :idProduto")
	Integer obterQuantidadeVendaProduto(@Param("idProduto") Long idProduto);
}
