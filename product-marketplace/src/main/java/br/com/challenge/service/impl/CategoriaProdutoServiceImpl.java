package br.com.challenge.service.impl;

import java.util.List;
import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.challenge.model.CategoriaProduto;
import br.com.challenge.repository.CategoriaProdutoRepository;
import br.com.challenge.service.CategoriaProdutoService;
import br.com.challenge.util.exception.ResourceNotFoundException;

@Service
public class CategoriaProdutoServiceImpl implements CategoriaProdutoService {

	@Autowired
	CategoriaProdutoRepository categoriaProdutoRepository;

	@Override
	public CategoriaProduto findById(Long id) {
		existsCategoriaProduto(id);

		return categoriaProdutoRepository.getOne(id);
	}

	@Override
	public List<CategoriaProduto> findAll() {
		return categoriaProdutoRepository.findAll();
	}

	private void existsCategoriaProduto(Long id) {

		if (Objects.isNull(id) || categoriaProdutoRepository.findById(id).orElse(null) == null) {
			throw new ResourceNotFoundException("Nenhuma categoria encontrada");
		}
	}

}
