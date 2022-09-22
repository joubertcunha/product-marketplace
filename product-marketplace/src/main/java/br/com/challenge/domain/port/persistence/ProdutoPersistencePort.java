package br.com.challenge.domain.port.persistence;

import br.com.challenge.infrastructure.entity.Produto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface ProdutoPersistencePort {

    Page<Produto> findAll(Pageable pageable);

    List<Produto> findAll();

    Produto save(Produto produto);

    Page<Produto> findByNomeContains(String termoPesquisado, Pageable pageable);

    void deleteById(Long id);

    void existsProduct(Long id);

    Produto getOne(Long id);
}
