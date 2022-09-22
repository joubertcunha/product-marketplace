package br.com.challenge.infrastructure.schedule;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import br.com.challenge.domain.port.api.ProdutoPort;

@Component
public class DailyScoreTasks {
	
	@Autowired
	private ProdutoPort produtoPort;

	/**
	*  O job deverá ser executado 1 veze ao dia, calculando o score do dia anterior.:
	*  00:10 irá calcular o score dos produtos no dia anterior.
	**/
	// TODO Scheduled para testes
	@Scheduled(cron = "*/50 * * * * *")
	public void calcularScoreProduto() {
		produtoPort.calcularScoreProduto();
	}
	
}
