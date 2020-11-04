package br.com.challenge.util.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import br.com.challenge.controller.dto.NewsApiDTO;

@FeignClient(name = "newsApiClient", url = "https://newsapi.org/")
public interface NewsApiClient {

	@GetMapping(value = "/v2/everything")
	public NewsApiDTO getNewsApi(@RequestParam(value="language") String language,
            @RequestParam(value="q") String q,
            @RequestParam(value="from") String from,
            @RequestParam(value="to") String to,
            @RequestParam(value="apiKey") String apiKey);
		
}
