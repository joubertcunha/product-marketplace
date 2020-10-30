package br.com.challenge.controller;

import java.net.URI;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import br.com.challenge.controller.dto.ProdutoDTO;
import br.com.challenge.model.Produto;
import br.com.challenge.service.ProdutoService;
import br.com.challenge.util.ModelMapperUtil;

@RestController
@RequestMapping("produto")
public class ProdutoController {

	@Autowired
	private ProdutoService produtoService;

	@Autowired
	private ModelMapperUtil modelMapper;

	@GetMapping
	public ResponseEntity<Page<ProdutoDTO>> listaAll(@PageableDefault(page = 0, size = 10, sort = "id") Pageable pageable) {
		return ResponseEntity.ok(modelMapper.convertPage(produtoService.listAll(pageable), ProdutoDTO.class));
	}

	@GetMapping("/{id}")
	public ResponseEntity<?> findById(@PathVariable Long id) {
		return ResponseEntity.ok(modelMapper.convert(produtoService.findById(id), ProdutoDTO.class));
	}

	@PostMapping
	public ResponseEntity<ProdutoDTO> save(@RequestBody ProdutoDTO produtoDTO, UriComponentsBuilder uriBuilder) {
		Produto produto = produtoService.save(modelMapper.convert(produtoDTO, Produto.class));

		URI uri = uriBuilder.path("/produto/{id}").buildAndExpand(produto.getId()).toUri();
		return ResponseEntity.created(uri).body(modelMapper.convert(produto, ProdutoDTO.class));
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<?> remove(@PathVariable Long id) {
		produtoService.deleteById(id);
		return ResponseEntity.ok().build();
	}
}
