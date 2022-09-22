package br.com.challenge.domain.data;

import java.util.List;

import lombok.Data;

@Data
public class NewsApiDTO {

	private String status;
	private int totalResults;
	private List<ArticlesDTO> articles;
}
