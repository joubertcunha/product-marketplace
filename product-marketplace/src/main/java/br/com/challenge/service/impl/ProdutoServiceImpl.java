package br.com.challenge.service.impl;

import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import br.com.challenge.model.Produto;
import br.com.challenge.repository.ProdutoRepository;
import br.com.challenge.service.CategoriaProdutoService;
import br.com.challenge.service.ProdutoService;

@Service
public class ProdutoServiceImpl implements ProdutoService {

	@Autowired
	private ProdutoRepository produtoRepository;
	
	@Autowired
	private CategoriaProdutoService categoriaProdutoService;

	public Page<Produto> listAll(Pageable pageable) {
		Page<Produto> produtos = produtoRepository.findAll(pageable);

		if (produtos.isEmpty()) {

		}
		return produtos;
	}

	public Produto findById(Long id) {
		existsProduct(id);

		return produtoRepository.getOne(id);
	}

	public Produto save(Produto produto) {
		produto.setCategoriaProduto(categoriaProdutoService.findById(produto.getCategoriaProduto().getId()));
		return produtoRepository.save(produto);
	}

	public void deleteById(Long id) {
		existsProduct(id);

		produtoRepository.deleteById(id);
	}

	private void existsProduct(Long id) {
		if (Objects.isNull(id) || produtoRepository.findById(id).orElse(null) == null) {
			return;
		}

	}
}
