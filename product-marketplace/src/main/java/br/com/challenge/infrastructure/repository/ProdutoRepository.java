package br.com.challenge.infrastructure.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import br.com.challenge.infrastructure.entity.Produto;

@Repository
public interface ProdutoRepository extends JpaRepository<Produto, Long> {
	
	Page<Produto> findByNomeContains(@Param("termoPesquisado") String termoPesquisado, Pageable pageable);
}
