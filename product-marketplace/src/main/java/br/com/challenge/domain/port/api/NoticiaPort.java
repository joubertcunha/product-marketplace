package br.com.challenge.domain.port.api;

import br.com.challenge.infrastructure.entity.CategoriaProduto;

public interface NoticiaPort {

	void newsApiDownload();
	
	Integer obterQuantidadeNoticiasCategoriaProdutoDia(CategoriaProduto categoriaProduto);
}
