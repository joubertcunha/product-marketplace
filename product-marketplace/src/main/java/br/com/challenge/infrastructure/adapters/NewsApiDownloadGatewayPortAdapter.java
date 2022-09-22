package br.com.challenge.infrastructure.adapters;

import br.com.challenge.domain.data.NewsApiDTO;
import br.com.challenge.domain.port.gateway.NewsApiDownloadGatewayPort;
import br.com.challenge.infrastructure.adapters.client.NewsApiClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class NewsApiDownloadGatewayPortAdapter implements NewsApiDownloadGatewayPort {

    @Autowired
    private NewsApiClient newsApiClient;

    @Value("${newsapi.key.value}")
    private String apiKey;

    @Override
    public NewsApiDTO getNewsApi(String language, String q, String dataInicio, String dataFim) {
        return newsApiClient.getNewsApi(
                language,
                q,
                dataInicio,
                dataFim,
                apiKey);
    }

}
