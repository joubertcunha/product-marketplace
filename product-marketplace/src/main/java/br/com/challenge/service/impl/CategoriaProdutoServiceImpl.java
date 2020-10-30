package br.com.challenge.service.impl;

import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.challenge.model.CategoriaProduto;
import br.com.challenge.repository.CategoriaProdutoRepository;
import br.com.challenge.service.CategoriaProdutoService;

@Service
public class CategoriaProdutoServiceImpl implements CategoriaProdutoService {

	@Autowired
	CategoriaProdutoRepository categoriaProdutoRepository;

	public CategoriaProduto findById(Long id) {
		existsCategoriaProduto(id);

		return categoriaProdutoRepository.getOne(id);
	}

	private void existsCategoriaProduto(Long id) {

		if (Objects.isNull(id) || categoriaProdutoRepository.findById(id).orElse(null) == null) {

		}
	}
}
