package br.com.challenge.infrastructure.mappers;

import java.util.List;
import java.util.stream.Collectors;

import org.apache.commons.lang3.StringUtils;
import org.modelmapper.ModelMapper;
import org.modelmapper.PropertyMap;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Component;

import br.com.challenge.domain.data.ArticlesDTO;
import br.com.challenge.infrastructure.entity.Noticia;

@Component
public class ModelMapperUtil {

	public <T, O> T convert(O obj, Class<T> objClass) {
		return getModelMapper().map(obj, objClass);
	}

	public <T, O> List<T> convertList(List<O> objList, Class<T> objClass) {
		return objList.stream().map(item -> getModelMapper().map(item, objClass)).collect(Collectors.toList());
	}

	public <T, O> Page<T> convertPage(Page<O> objPage, Class<T> objClass) {
		return objPage.map(item -> getModelMapper().map(item, objClass));
	}

	public Noticia convertNewsApi(ArticlesDTO articlesDTO, Class<Noticia> noticia) {
		return getModelMapperNewsApi().map(articlesDTO, noticia);
	}

	private ModelMapper getModelMapper() {
		ModelMapper modelMapper = new ModelMapper();
		modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
		return modelMapper;
	}

	private ModelMapper getModelMapperNewsApi() {
		ModelMapper modelMapper = getModelMapper();
		modelMapper.addMappings(new PropertyMap<ArticlesDTO, Noticia>() {
			@Override
			protected void configure() {
				map().setAutor(source.getAuthor());
				map().setTitulo(source.getTitle());
				map().setDescricao(source.getDescription());
				map().setConteudo(source.getContent());
				map().setUrl(source.getUrl());
				map().setDataPublicada(source.getPublishedAt());
			}
		}).setPostConverter(context -> {
			Noticia noticia = context.getDestination();
			noticia.setAutor(StringUtils.truncate(noticia.getConteudo(), 70));
			noticia.setTitulo(StringUtils.truncate(noticia.getConteudo(), 100));
			noticia.setUrl(StringUtils.truncate(noticia.getConteudo(), 150));
			noticia.setConteudo(StringUtils.truncate(noticia.getConteudo(), 255));
			noticia.setDescricao(StringUtils.truncate(noticia.getDescricao(), 150));
			
			return noticia;
		});
		return modelMapper;
	}
}
