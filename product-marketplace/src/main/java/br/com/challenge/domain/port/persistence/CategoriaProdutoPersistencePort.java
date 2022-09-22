package br.com.challenge.domain.port.persistence;

import br.com.challenge.infrastructure.entity.CategoriaProduto;

import java.util.List;

public interface CategoriaProdutoPersistencePort {

    CategoriaProduto findById(Long id);

    List<CategoriaProduto> findAll();

}
