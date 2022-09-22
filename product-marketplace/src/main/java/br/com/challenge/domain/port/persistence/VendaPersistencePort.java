package br.com.challenge.domain.port.persistence;

import java.util.Date;

public interface VendaPersistencePort {

    Double obterMediaAvaliacaoProduto(Long idProduto, Date dataInicio, Date dataFim);

    Integer obterQuantidadeVendasProduto(Long id);
}
