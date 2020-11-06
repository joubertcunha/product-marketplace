package br.com.challenge.service.impl;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.challenge.controller.dto.NewsApiDTO;
import br.com.challenge.model.CategoriaProduto;
import br.com.challenge.model.Noticia;
import br.com.challenge.repository.NoticiaRepository;
import br.com.challenge.service.CategoriaProdutoService;
import br.com.challenge.service.NoticiaService;
import br.com.challenge.util.ModelMapperUtil;
import br.com.challenge.util.NewsApiUtil;
import br.com.challenge.util.client.NewsApiClient;

@Service
public class NoticiaServiceImpl implements NoticiaService {

	@Autowired
	private CategoriaProdutoService categoriaProdutoService;

	@Autowired
	private NoticiaRepository noticiaRepository;

	@Autowired
	private NewsApiClient newsApiClient;
	
	@Autowired
	private NewsApiUtil newsApiUtil;

	@Autowired
	private ModelMapperUtil modelMapper;

	@Override
	public void newsApiDownload() {
		DateTimeFormatter ofPattern = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss");
		NewsApiDTO newsApi = null;

		List<CategoriaProduto> categorias = categoriaProdutoService.findAll();
		for (CategoriaProduto categoria : categorias) {

			newsApi = newsApiClient.getNewsApi(
					newsApiUtil.getLanguage(), 
					newsApiUtil.getQ(categoria.getNome()),
					ofPattern.format(newsApiUtil.getTime().inicio), 
					ofPattern.format(newsApiUtil.getTime().fim),
					newsApiUtil.getApiKey());

			if (!"error".equals(newsApi.getStatus()) && newsApi.getTotalResults() > 0) {
				newsApi.getArticles().forEach(conteudo -> {
					Noticia noticia = modelMapper.convertNewsApi(conteudo, Noticia.class);
					noticia.setCategoriaProduto(categoria);
					noticiaRepository.save(noticia);
				});
			}
		}
	}
	
	@Override
	public Integer obterQuantidadeNoticiasCategoriaProdutoDia(CategoriaProduto categoriaProduto) {
		LocalDateTime dataInicio = LocalDateTime.now().with(LocalTime.MIDNIGHT);
		LocalDateTime dataFim = dataInicio.withHour(23).withMinute(59).withSecond(59);

		return noticiaRepository.obterQuantidadeNoticiaCategoria(categoriaProduto.getId(),
				Date.from(dataInicio.atZone(ZoneId.systemDefault()).toInstant()),
				Date.from(dataFim.atZone(ZoneId.systemDefault()).toInstant()));
	}

}
