package br.com.challenge.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.challenge.model.Produto;

@Repository
public interface ProdutoRepository extends JpaRepository<Produto, Long>{

}
