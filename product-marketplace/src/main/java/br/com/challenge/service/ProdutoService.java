package br.com.challenge.service;

import java.util.List;

import br.com.challenge.model.Produto;

public interface ProdutoService {

	List<Produto> listAll();

	Produto findById(Long id);

	Produto save(Produto produto);

	void deleteById(Long id);
}
