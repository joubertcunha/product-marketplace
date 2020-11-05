package br.com.challenge.util;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import br.com.challenge.util.enumeration.EnumNewsApiDownload;

@Component
public class NewsApiUtil {
	
	@Value("${newsapi.key.value}")
	private String apiKey;

	public String getLanguage() {
		return "pt";
	}

	public String getQ(String categoria) {
		return categoria;
	}

	public EnumNewsApiDownload getTime() {
		return EnumNewsApiDownload.getPeriod();
	}
	
	public String getApiKey() {
		return this.apiKey;
	}

}
