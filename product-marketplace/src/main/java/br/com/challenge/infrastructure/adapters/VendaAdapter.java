package br.com.challenge.infrastructure.adapters;

import br.com.challenge.domain.port.persistence.VendaPersistencePort;
import br.com.challenge.infrastructure.repository.VendaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class VendaAdapter implements VendaPersistencePort {

    @Autowired
    private VendaRepository vendaRepository;

    @Override
    public Double obterMediaAvaliacaoProduto(Long idProduto, Date dataInicio, Date dataFim) {
        return vendaRepository.obterMediaAvaliacaoProduto(idProduto, dataInicio, dataFim);
    }

    @Override
    public Integer obterQuantidadeVendasProduto(Long id) {
        return vendaRepository.obterQuantidadeVendaProduto(id);
    }
}
