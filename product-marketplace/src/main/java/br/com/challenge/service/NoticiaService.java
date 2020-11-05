package br.com.challenge.service;

import br.com.challenge.model.CategoriaProduto;

public interface NoticiaService {

	void newsApiDownload();
	
	Integer obterQuantidadeNoticiasCategoriaProdutoDia(CategoriaProduto categoriaProduto);
}
