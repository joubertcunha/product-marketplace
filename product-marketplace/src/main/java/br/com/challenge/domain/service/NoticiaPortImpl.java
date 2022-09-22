package br.com.challenge.domain.service;

import br.com.challenge.domain.data.NewsApiDTO;
import br.com.challenge.domain.port.api.NoticiaPort;
import br.com.challenge.domain.port.gateway.NewsApiDownloadGatewayPort;
import br.com.challenge.domain.port.persistence.CategoriaProdutoPersistencePort;
import br.com.challenge.domain.port.persistence.NoticiaPersistencePort;
import br.com.challenge.infrastructure.entity.CategoriaProduto;
import br.com.challenge.infrastructure.entity.Noticia;
import br.com.challenge.infrastructure.mappers.ModelMapperUtil;
import br.com.challenge.infrastructure.enumeration.EnumNewsApiDownload;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.List;

@Service
public class NoticiaPortImpl implements NoticiaPort {

    @Autowired
    private CategoriaProdutoPersistencePort categoriaProdutoPersistencePort;

    @Autowired
    private NoticiaPersistencePort noticiaPersistencePort;
    @Autowired
    private ModelMapperUtil modelMapper;
    @Autowired
    private NewsApiDownloadGatewayPort newsApiDownloadGatewayPort;

    @Override
    public void newsApiDownload() {
        DateTimeFormatter ofPattern = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss");
        NewsApiDTO newsApi;

        List<CategoriaProduto> categorias = categoriaProdutoPersistencePort.findAll();
        for (CategoriaProduto categoria : categorias) {

            EnumNewsApiDownload period = EnumNewsApiDownload.getPeriod();

            newsApi = newsApiDownloadGatewayPort.getNewsApi(
                    "pt",
                    categoria.getNome(),
                    ofPattern.format(period.inicio),
                    ofPattern.format(period.fim)
            );

            if (!"error".equals(newsApi.getStatus()) && newsApi.getTotalResults() > 0) {
                newsApi.getArticles().forEach(conteudo -> {
                    // Retirar a conversão usando a lib modelMapper da camada RN
                    Noticia noticia = modelMapper.convertNewsApi(conteudo, Noticia.class);
                    noticia.setCategoriaProduto(categoria);
                    noticiaPersistencePort.save(noticia);
                });
            }
        }
    }

    @Override
    public Integer obterQuantidadeNoticiasCategoriaProdutoDia(CategoriaProduto categoriaProduto) {
        LocalDateTime dataInicio = LocalDateTime.now().with(LocalTime.MIDNIGHT);
        LocalDateTime dataFim = dataInicio.withHour(23).withMinute(59).withSecond(59);

        return noticiaPersistencePort.obterQuantidadeNoticiasCategoriaProdutoDia(categoriaProduto.getId(),
                Date.from(dataInicio.atZone(ZoneId.systemDefault()).toInstant()),
                Date.from(dataFim.atZone(ZoneId.systemDefault()).toInstant()));
    }

}
