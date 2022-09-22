package br.com.challenge.domain.service;

import br.com.challenge.domain.port.api.VendaPort;
import br.com.challenge.domain.port.persistence.VendaPersistencePort;
import br.com.challenge.infrastructure.entity.Produto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.ZoneId;
import java.util.Date;

@Service
public class VendaServiceImpl implements VendaPort {

	@Autowired
	private VendaPersistencePort vendaPersistencePort;

	@Override
	public Double obterMediaAvaliacaoProduto12Meses(Produto produto) {
		LocalDateTime dataFim = LocalDateTime.now();
		LocalDateTime dataInicio = dataFim.with(LocalTime.MIDNIGHT).plusYears(-1);
		
		return vendaPersistencePort.obterMediaAvaliacaoProduto(produto.getId(),
				Date.from(dataInicio.atZone(ZoneId.systemDefault()).toInstant()),
				Date.from(dataFim.atZone(ZoneId.systemDefault()).toInstant()));
	}

	@Override
	public Integer obterQuantidadeVendasProduto(Produto produto) {
		return vendaPersistencePort.obterQuantidadeVendasProduto(produto.getId());
	}
	
}
