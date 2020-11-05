package br.com.challenge.util.client;

import org.springframework.stereotype.Component;

import br.com.challenge.controller.dto.NewsApiDTO;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
public class NewsAPiFallback implements NewsApiClient {

	@Override
	public NewsApiDTO getNewsApi(String language, String q, String from, String to, String apiKey) {
		System.out.println("getNewsApi - fallback");

		return new NewsApiDTO();
	}

}
