package br.com.challenge.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import br.com.challenge.model.Produto;

public interface ProdutoService {

	Page<Produto> listAll(Pageable pageable);

	Produto findById(Long id);

	Produto save(Produto produto);
	
	Produto update(Long id, Produto produto);
	
	Page<Produto> obterProdutosRanqueados(String termoPesquisado, Pageable pageable);

	void deleteById(Long id);
	
	void calcularScoreProduto();
}
