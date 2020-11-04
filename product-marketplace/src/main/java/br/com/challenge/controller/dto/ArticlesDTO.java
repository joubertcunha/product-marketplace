package br.com.challenge.controller.dto;

import java.util.Date;

import lombok.Data;

@Data
public class ArticlesDTO {

	private String author;
	private String title;
	private String description;
	private String url;
	private Date publishedAt;
	private String content;
	
}
