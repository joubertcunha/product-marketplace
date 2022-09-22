package br.com.challenge.infrastructure.adapters.client;

import org.springframework.stereotype.Component;

import br.com.challenge.domain.data.NewsApiDTO;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
public class NewsAPiFallback implements NewsApiClient {

	@Override
	public NewsApiDTO getNewsApi(String language, String q, String from, String to, String apiKey) {
		log.info("getNewsApi - fallback");
		return new NewsApiDTO();
	}

}
