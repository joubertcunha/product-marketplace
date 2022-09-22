package br.com.challenge.infrastructure.adapters;

import br.com.challenge.domain.port.persistence.CategoriaProdutoPersistencePort;
import br.com.challenge.infrastructure.entity.CategoriaProduto;
import br.com.challenge.infrastructure.repository.CategoriaProdutoRepository;
import br.com.challenge.infrastructure.adapters.exception.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Objects;

@Component
public class CategoriaProdutoAdapter implements CategoriaProdutoPersistencePort {

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
