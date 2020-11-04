package br.com.challenge.service;

import java.util.List;

import br.com.challenge.model.CategoriaProduto;

public interface CategoriaProdutoService {

	CategoriaProduto findById(Long id);

	List<CategoriaProduto> findAll();
	
}
