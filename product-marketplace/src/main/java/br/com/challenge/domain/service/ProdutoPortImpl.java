package br.com.challenge.domain.service;

import br.com.challenge.domain.port.api.NoticiaPort;
import br.com.challenge.domain.port.api.ProdutoPort;
import br.com.challenge.domain.port.api.VendaPort;
import br.com.challenge.domain.port.persistence.CategoriaProdutoPersistencePort;
import br.com.challenge.domain.port.persistence.ProdutoPersistencePort;
import br.com.challenge.infrastructure.entity.Produto;
import br.com.challenge.infrastructure.adapters.exception.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.temporal.ChronoUnit;
import java.util.Date;
import java.util.List;

@Service
public class ProdutoPortImpl implements ProdutoPort {

    @Autowired
    private ProdutoPersistencePort produtoPersistencePort;

    @Autowired
    private CategoriaProdutoPersistencePort categoriaProdutoPersistencePort;

    @Autowired
    private VendaPort vendaPort;

    @Autowired
    private NoticiaPort noticiaPort;

    @Override
    public Page<Produto> listAll(Pageable pageable) {
        Page<Produto> produtos = produtoPersistencePort.findAll(pageable);

        if (produtos.isEmpty()) {
            throw new ResourceNotFoundException("Nenhum produto encontrado");
        }
        return produtos;
    }

    @Override
    public Produto save(Produto produto) {
        produto.setDataCadastro(new Date());
        produto.setCategoriaProduto(categoriaProdutoPersistencePort.findById(produto.getCategoriaProduto().getId()));
        return produtoPersistencePort.save(produto);
    }

    @Override
    public Produto update(Long id, Produto produto) {
        produtoPersistencePort.existsProduct(id);

        Produto produtoAlterado = produtoPersistencePort.getOne(id);
        produtoAlterado.setNome(produto.getNome());
        produtoAlterado.setDescricao(produto.getDescricao());
        produtoAlterado.setScore(produto.getScore());
        return produtoPersistencePort.save(produtoAlterado);
    }

    @Override
    public void deleteById(Long id) {
        produtoPersistencePort.existsProduct(id);

        produtoPersistencePort.deleteById(id);
    }

    @Override
    public Produto findById(Long id) {
        produtoPersistencePort.existsProduct(id);

        return produtoPersistencePort.getOne(id);
    }

    public Page<Produto> obterProdutosRanqueados(String termoPesquisado, Pageable pageable) {
        return produtoPersistencePort.findByNomeContains(termoPesquisado, pageable);
    }

    @Override
    public void calcularScoreProduto() {
        List<Produto> produtos = produtoPersistencePort.findAll();

        BigDecimal score;
        for (Produto produto : produtos) {
            score = calcularValorX(produto).add(calcularValorY(produto)).add(calcularValorZ(produto));

            produto.setScore(score.doubleValue());
            produtoPersistencePort.save(produto);
        }

    }

    // X = Média de avaliação do produto nos últimos 12 meses
    private BigDecimal calcularValorX(Produto produto) {
        Double mediaAvaliacao = vendaPort.obterMediaAvaliacaoProduto12Meses(produto);

        if (mediaAvaliacao == null) {
            return BigDecimal.ZERO;
        }

        return new BigDecimal(mediaAvaliacao).setScale(2, BigDecimal.ROUND_UP);
    }

    // Y = Quantidade de vendas/dias que o produto existe
    private BigDecimal calcularValorY(Produto produto) {
        long qtdVendas = vendaPort.obterQuantidadeVendasProduto(produto).longValue();
        long qtdDiasProdutoCadastrado = ChronoUnit.DAYS.between(produto.getDataCadastro().toInstant().atZone(ZoneId.systemDefault()).toLocalDate(), LocalDate.now());

        if (qtdDiasProdutoCadastrado == 0) {
            return BigDecimal.ZERO;
        }

        return new BigDecimal((double) qtdVendas / qtdDiasProdutoCadastrado).setScale(2, BigDecimal.ROUND_UP);
    }

    // Z = Quantidade de notícias da categoria do produto no dia corrente
    private BigDecimal calcularValorZ(Produto produto) {
        Integer qtdNoticiasCategoriaProdutoDia = noticiaPort.obterQuantidadeNoticiasCategoriaProdutoDia(produto.getCategoriaProduto());

        if (qtdNoticiasCategoriaProdutoDia == null) {
            return BigDecimal.ZERO;
        }

        return new BigDecimal(qtdNoticiasCategoriaProdutoDia);
    }

}
