package br.com.challenge.util.schedule;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import br.com.challenge.service.NoticiaService;

@Component
public class NewsApiTasks {

	@Autowired
	private NoticiaService noticiaService;

	@Scheduled(cron = "*/10 * * * * *")
	public void newsApiDownload() {
		noticiaService.newsApiDownload();
	}
}
