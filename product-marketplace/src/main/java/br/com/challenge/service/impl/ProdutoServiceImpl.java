package br.com.challenge.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.challenge.model.Produto;
import br.com.challenge.repository.ProdutoRepository;
import br.com.challenge.service.ProdutoService;

@Service
public class ProdutoServiceImpl implements ProdutoService {

	@Autowired
	private ProdutoRepository produtoRepository;

	public List<Produto> listAll() {
		List<Produto> produtos = produtoRepository.findAll();

		if (produtos.isEmpty()) {

		}
		return produtos;
	}

	public Produto findById(Long id) {
		existsProduct(id);

		return produtoRepository.getOne(id);
	}

	public Produto save(Produto produto) {
		return produtoRepository.save(produto);
	}

	public void deleteById(Long id) {
		existsProduct(id);

		produtoRepository.deleteById(id);
	}

	private void existsProduct(Long id) {
		if (produtoRepository.findById(id).orElse(null) == null) {
			return;
		}

	}
}
