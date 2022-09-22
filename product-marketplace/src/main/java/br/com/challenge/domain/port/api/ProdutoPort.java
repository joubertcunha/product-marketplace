package br.com.challenge.domain.port.api;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import br.com.challenge.infrastructure.entity.Produto;

public interface ProdutoPort {

	Page<Produto> listAll(Pageable pageable);
	Produto save(Produto produto);
	Produto update(Long id, Produto produto);
	Page<Produto> obterProdutosRanqueados(String termoPesquisado, Pageable pageable);
	void deleteById(Long id);
	Produto findById(Long id);
	void calcularScoreProduto();
}