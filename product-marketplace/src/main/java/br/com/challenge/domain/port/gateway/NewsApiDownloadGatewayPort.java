package br.com.challenge.domain.port.gateway;

import br.com.challenge.domain.data.NewsApiDTO;

public interface NewsApiDownloadGatewayPort {
    NewsApiDTO getNewsApi(String language, String q, String dataInicio, String dataFim);
}
