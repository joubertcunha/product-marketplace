package br.com.challenge.infrastructure.adapters;

import br.com.challenge.domain.port.persistence.ProdutoPersistencePort;
import br.com.challenge.infrastructure.entity.Produto;
import br.com.challenge.infrastructure.repository.ProdutoRepository;
import br.com.challenge.infrastructure.adapters.exception.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Objects;

@Component
public class ProdutoAdapter implements ProdutoPersistencePort {

    @Autowired
    private ProdutoRepository produtoRepository;

    @Override
    public Page<Produto> findAll(Pageable pageable) {
        return produtoRepository.findAll(pageable);
    }

    @Override
    public List<Produto> findAll() {
        return produtoRepository.findAll();
    }

    @Override
    public Produto save(Produto produto) {
        return produtoRepository.save(produto);
    }

    @Override
    public void deleteById(Long id) {
        produtoRepository.deleteById(id);
    }

    public void existsProduct(Long id) {
        if (Objects.isNull(id) || produtoRepository.findById(id).orElse(null) == null) {
            throw new ResourceNotFoundException("Nenhum produto encontrado");
        }
    }

    public Produto getOne(Long id) {
        return produtoRepository.getOne(id);
    }

    public Page<Produto> findByNomeContains(String termoPesquisado, Pageable pageable) {
        Page<Produto> produto = produtoRepository.findByNomeContains(termoPesquisado, pageable);
        if (produto.isEmpty()) {
            throw new ResourceNotFoundException("Nenhum produto encontrado");
        }

        return produto;
    }
}
