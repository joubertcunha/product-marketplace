package br.com.challenge.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.challenge.controller.dto.ArticlesDTO;
import br.com.challenge.controller.dto.NewsApiDTO;
import br.com.challenge.model.CategoriaProduto;
import br.com.challenge.model.Noticia;
import br.com.challenge.repository.NoticiaRepository;
import br.com.challenge.service.CategoriaProdutoService;
import br.com.challenge.service.NoticiaService;
import br.com.challenge.util.ModelMapperUtil;
import br.com.challenge.util.client.NewsApiClient;

@Service
public class NoticiaServiceImpl implements NoticiaService {

	@Autowired
	private CategoriaProdutoService categoriaProdutoService;
	
	@Autowired
	private NoticiaRepository noticiaRepository;

	@Autowired
	private NewsApiClient newsApiClient;
	
	@Autowired ModelMapperUtil modelMapper;

	public void newsApiDownload() {
		List<CategoriaProduto> categorias = categoriaProdutoService.findAll();

		for (CategoriaProduto categoria : categorias) {
			System.out.println(categoria.getNome());

			NewsApiDTO newsApi = newsApiClient.getNewsApi("pt", "esporte", "2020-11-03T08:00:00", "2020-11-03T12:00:00",
					"1846d24618ad4ac2af30d93510054dff");

			newsApi.getArticles().forEach(conteudo -> {
				Noticia noticia = modelMapper.convertNewsApi(conteudo, Noticia.class);
				noticia.setCategoriaProduto(categoria);
				noticiaRepository.save(noticia);
			});
		}
	}

}
