package br.com.challenge.util.schedule;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import br.com.challenge.service.NoticiaService;

@Component
public class NewsApiTasks {

	@Autowired
	private NoticiaService noticiaService;

	/**
	*  O job deverá ser executado 4 vezes ao dia:
	*  06:00  no qual irá pegar as noticias publicadas de 00:00 às 06:00
	*  12:00  no qual irá pegar as noticias publicadas de 06:01 am às 12:00
	*  18:00  no qual irá pegar as noticias publicadas de 12:01 às 18:00
	*  23:59  no qual irá pegar as noticias publicadas de 18:01 às 23:59:59
	**/
	// TODO Scheduled para testes
	@Scheduled(cron = "*/30 * * * * *")
	public void newsApiDownload() {
		noticiaService.newsApiDownload();
	}
}
