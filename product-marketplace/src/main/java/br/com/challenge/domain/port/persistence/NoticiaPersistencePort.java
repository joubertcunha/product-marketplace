package br.com.challenge.domain.port.persistence;

import br.com.challenge.infrastructure.entity.Noticia;

import java.util.Date;

public interface NoticiaPersistencePort {

    Integer obterQuantidadeNoticiasCategoriaProdutoDia(Long idCategoria, Date dataInicio, Date dataFim);

    void save(Noticia noticia);
}
