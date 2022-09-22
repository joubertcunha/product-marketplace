package br.com.challenge.domain.port.api;

import br.com.challenge.infrastructure.entity.Produto;

public interface VendaPort {

	Double obterMediaAvaliacaoProduto12Meses(Produto produto);

	Integer obterQuantidadeVendasProduto(Produto produto);
}
