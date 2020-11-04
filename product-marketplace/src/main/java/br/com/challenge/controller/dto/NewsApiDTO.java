package br.com.challenge.controller.dto;

import java.util.List;

import lombok.Data;

@Data
public class NewsApiDTO {

	private String status;
	private int totalResults;
	private List<ArticlesDTO> articles;
}
