package br.com.challenge.service.impl;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.temporal.ChronoUnit;
import java.util.Date;
import java.util.List;
import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import br.com.challenge.model.Produto;
import br.com.challenge.repository.ProdutoRepository;
import br.com.challenge.service.CategoriaProdutoService;
import br.com.challenge.service.NoticiaService;
import br.com.challenge.service.ProdutoService;
import br.com.challenge.service.VendaService;
import br.com.challenge.util.exception.ResourceNotFoundException;

@Service
public class ProdutoServiceImpl implements ProdutoService {

	@Autowired
	private ProdutoRepository produtoRepository;
	
	@Autowired
	private CategoriaProdutoService categoriaProdutoService;
	
	@Autowired
	private VendaService vendaService;
	
	@Autowired
	private NoticiaService noticiaService;

	@Override
	public Page<Produto> listAll(Pageable pageable) {
		Page<Produto> produtos = produtoRepository.findAll(pageable);

		if (produtos.isEmpty()) {
			throw new ResourceNotFoundException("Nenhum produto encontrado");
		}
		return produtos;
	}
	
	@Override
	public Produto findById(Long id) {
		existsProduct(id);

		return produtoRepository.getOne(id);
	}

	@Override
	public Produto save(Produto produto) {
		produto.setDataCadastro(new Date());
		produto.setCategoriaProduto(categoriaProdutoService.findById(produto.getCategoriaProduto().getId()));
		return produtoRepository.save(produto);
	}
	
	@Override
	public Produto update(Long id, Produto produto) {
		existsProduct(id);
		
		produto.setId(id);
		return produtoRepository.save(produto);
	}	

	@Override
	public void deleteById(Long id) {
		existsProduct(id);

		produtoRepository.deleteById(id);
	}
	
	public Page<Produto> obterProdutosRanqueados(String termoPesquisado, Pageable pageable) {
		Page<Produto> produtos = produtoRepository.findByNomeContains(termoPesquisado, pageable);
		
		if(produtos.isEmpty()) {
			throw new ResourceNotFoundException("Nenhum produto encontrado");
		}
		
		return produtos;
	}

	@Override
	public void calcularScoreProduto() {
		List<Produto> produtos = produtoRepository.findAll();

		BigDecimal score = new BigDecimal(0);
		for (Produto produto : produtos) {
			score = calcularValorX(produto).add(calcularValorY(produto)).add(calcularValorZ(produto));
			
			produto.setScore(score.doubleValue());
			produtoRepository.save(produto);
		}

	}

	// X = Média de avaliação do produto nos últimos 12 meses
	private BigDecimal calcularValorX(Produto produto) {
		 Double mediaAvaliacao = vendaService.obterMediaAvaliacaoProduto12Meses(produto);
		 
		 if(mediaAvaliacao == null) {
			return BigDecimal.ZERO;
		 }
		 
		 return new BigDecimal(mediaAvaliacao).setScale(2, BigDecimal.ROUND_UP);
	}

	// Y = Quantidade de vendas/dias que o produto existe
	private BigDecimal calcularValorY(Produto produto) {
		 long qtdVendas = vendaService.obterQuantidadeVendasProduto(produto).longValue();
		 long qtdDiasProdutoCadastrado = ChronoUnit.DAYS.between(produto.getDataCadastro().toInstant().atZone(ZoneId.systemDefault()).toLocalDate(), LocalDate.now());
		 
		 if(qtdDiasProdutoCadastrado == 0) {
			 return BigDecimal.ZERO;
		 }
		 
		 return new BigDecimal((double) qtdVendas/qtdDiasProdutoCadastrado).setScale(2, BigDecimal.ROUND_UP);
	}

	// Z = Quantidade de notícias da categoria do produto no dia corrente
	private BigDecimal calcularValorZ(Produto produto) {
		Integer qtdNoticiasCategoriaProdutoDia = noticiaService.obterQuantidadeNoticiasCategoriaProdutoDia(produto.getCategoriaProduto());
		
		if(qtdNoticiasCategoriaProdutoDia == null) {
			return BigDecimal.ZERO;
		}
		
		return new BigDecimal(qtdNoticiasCategoriaProdutoDia);
	}

	private void existsProduct(Long id) {
		if (Objects.isNull(id) || produtoRepository.findById(id).orElse(null) == null) {
			throw new ResourceNotFoundException("Nenhum produto encontrado");
		}
	}

}
