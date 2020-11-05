package br.com.challenge.service;

import br.com.challenge.model.Produto;

public interface VendaService {

	Double obterMediaAvaliacaoProduto12Meses(Produto produto);

	Integer obterQuantidadeVendasProduto(Produto produto);
}
