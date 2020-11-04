package br.com.challenge.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.challenge.model.Noticia;

public interface NoticiaRepository extends JpaRepository<Noticia, Long>{

}
