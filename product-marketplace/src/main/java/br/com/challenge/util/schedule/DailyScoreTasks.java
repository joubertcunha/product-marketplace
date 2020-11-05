package br.com.challenge.util.schedule;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import br.com.challenge.service.ProdutoService;

@Component
public class DailyScoreTasks {
	
	@Autowired
	private ProdutoService produtoService;

//	@Scheduled(cron = "*/10 * * * * *")
	public void calcularScoreProduto() {
		produtoService.calcularScoreProduto();
	}
	
}
