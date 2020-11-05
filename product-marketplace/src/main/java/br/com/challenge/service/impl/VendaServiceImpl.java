package br.com.challenge.service.impl;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.ZoneId;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.challenge.model.Produto;
import br.com.challenge.repository.VendaRepository;
import br.com.challenge.service.VendaService;

@Service
public class VendaServiceImpl implements VendaService {

	@Autowired
	private VendaRepository vendaRepository;

	@Override
	public Double obterMediaAvaliacaoProduto12Meses(Produto produto) {
		LocalDateTime dataFim = LocalDateTime.now();
		LocalDateTime dataInicio = dataFim.with(LocalTime.MIDNIGHT).plusYears(-1);
		
		return vendaRepository.obterMediaAvaliacaoProduto(produto.getId(),
				Date.from(dataInicio.atZone(ZoneId.systemDefault()).toInstant()),
				Date.from(dataFim.atZone(ZoneId.systemDefault()).toInstant()));
	}

	@Override
	public Integer obterQuantidadeVendasProduto(Produto produto) {
		return vendaRepository.obterQuantidadeVendaProduto(produto.getId());
	}
	
}
