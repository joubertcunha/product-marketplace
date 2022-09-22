package br.com.challenge.infrastructure.adapters;

import br.com.challenge.domain.port.persistence.NoticiaPersistencePort;
import br.com.challenge.infrastructure.entity.Noticia;
import br.com.challenge.infrastructure.repository.NoticiaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class NoticiaAdapter implements NoticiaPersistencePort {

    @Autowired
    private NoticiaRepository noticiaRepository;

    @Override
    public Integer obterQuantidadeNoticiasCategoriaProdutoDia(Long idCategoria, Date dataInicio, Date dataFim) {
        return noticiaRepository.obterQuantidadeNoticiaCategoria(idCategoria, dataInicio, dataFim);
    }

    @Override
    public void save(Noticia noticia) {
        noticiaRepository.save(noticia);
    }
}
