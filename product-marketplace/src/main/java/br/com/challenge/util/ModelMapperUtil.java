package br.com.challenge.util;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Component;

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

	private ModelMapper getModelMapper() {
		ModelMapper modelMapper = new ModelMapper();
		modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
		return modelMapper;
	}
}
