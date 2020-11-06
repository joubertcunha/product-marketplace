package br.com.challenge.util.schedule;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import br.com.challenge.service.ProdutoService;

@Component
public class DailyScoreTasks {
	
	@Autowired
	private ProdutoService produtoService;

	/**
	*  O job deverá ser executado 1 veze ao dia, calculando o score do dia anterior.:
	*  00:10 irá calcular o score dos produtos no dia anterior.
	**/
	// TODO Scheduled para testes
	@Scheduled(cron = "*/50 * * * * *")
	public void calcularScoreProduto() {
		produtoService.calcularScoreProduto();
	}
	
}
