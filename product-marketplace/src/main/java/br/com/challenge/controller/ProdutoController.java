package br.com.challenge.controller;

import java.net.URI;
import java.util.Date;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.web.PageableDefault;
import org.springframework.data.web.SortDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import br.com.challenge.controller.dto.ProdutoDTO;
import br.com.challenge.controller.dto.ProdutoRanqueadoDTO;
import br.com.challenge.model.Produto;
import br.com.challenge.service.ProdutoService;
import br.com.challenge.util.ModelMapperUtil;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@RestController
@RequestMapping("produto")
public class ProdutoController {

	@Autowired
	private ProdutoService produtoService;

	@Autowired
	private ModelMapperUtil modelMapper;

	@GetMapping
	@ApiOperation(value = "Retorna lista de produtos.", response = ProdutoDTO.class)
	@ApiResponses(value = { 
			@ApiResponse(code = 200, message = "Lista de produtos retornado com sucesso."),
			@ApiResponse(code = 404, message = "Nenhum produto não encontrado."), })
	public ResponseEntity<Page<ProdutoDTO>> listaAll(
			@PageableDefault(page = 0, size = 10, sort = "id") Pageable pageable) {
		return ResponseEntity.ok(modelMapper.convertPage(produtoService.listAll(pageable), ProdutoDTO.class));
	}

	@GetMapping("/{id}")
	@ApiOperation(value = "Retorna produto solicitado.", response = ProdutoDTO.class)
	@ApiResponses(value = { 
			@ApiResponse(code = 200, message = "Produto retornado com sucesso."),
			@ApiResponse(code = 404, message = "Nenhum produto não encontrado."), })
	public ResponseEntity<ProdutoDTO> findById(@PathVariable Long id) {
		return ResponseEntity.ok(modelMapper.convert(produtoService.findById(id), ProdutoDTO.class));
	}

	@PostMapping
	@ApiOperation(value = "Insere novo produto.")
	@ApiResponses(value = { 
			@ApiResponse(code = 201, message = "Produto criado com sucesso."),
			@ApiResponse(code = 400, message = "Solicitação inválida.") })
	public ResponseEntity<ProdutoDTO> save(@RequestBody @Valid ProdutoDTO produtoDTO, UriComponentsBuilder uriBuilder) {
		Produto produto = produtoService.save(modelMapper.convert(produtoDTO, Produto.class));

		URI uri = uriBuilder.path("/produto/{id}").buildAndExpand(produto.getId()).toUri();
		return ResponseEntity.created(uri).body(modelMapper.convert(produto, ProdutoDTO.class));
	}
	
	@PutMapping("/{id}")
	@ApiOperation(value = "Atualiza produto por ID.")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Produto atualizado com sucesso."),
			@ApiResponse(code = 400, message = "Solicitação inválida."),
			@ApiResponse(code = 404, message = "Nenhum produto encontrado.") })
	public ResponseEntity<ProdutoDTO> update(@PathVariable Long id, @Valid @RequestBody ProdutoDTO produtoDTO){
		Produto produto = produtoService.update(id, modelMapper.convert(produtoDTO, Produto.class));
		return ResponseEntity.ok(modelMapper.convert(produto, ProdutoDTO.class));
	}

	@DeleteMapping("/{id}")
	@ApiOperation(value = "Remove produto por ID.")
	@ApiResponses(value = { 
			@ApiResponse(code = 200, message = "Produto removido com sucesso."),
			@ApiResponse(code = 404, message = "Produto não encontrado.") })
	public ResponseEntity<?> remove(@PathVariable Long id) {
		produtoService.deleteById(id);
		return ResponseEntity.ok().build();
	}

	@GetMapping("/ranking")
	@ApiOperation(value = "Retorna lista de produtos ranqueados.", response = ProdutoRanqueadoDTO.class)
	@ApiResponses(value = { 
			@ApiResponse(code = 200, message = "Lista de produtos retornado com sucesso."),
			@ApiResponse(code = 404, message = "Nenhum produto não encontrado."), })
	public ResponseEntity<Page<ProdutoRanqueadoDTO>> getRankProduct(@RequestParam(value = "termoPesquisado", required = true) String termoPesquisado, 
			@PageableDefault(page = 0, size = 10) @SortDefault.SortDefaults({
				@SortDefault(sort = "score", direction = Direction.DESC),
				@SortDefault(sort = "nome", direction = Direction.ASC),
				@SortDefault(sort = "categoriaProduto.nome", direction = Direction.ASC)}) Pageable pageable) {
		Page<Produto> produtos = produtoService.obterProdutosRanqueados(termoPesquisado, pageable);
		
		Page<ProdutoRanqueadoDTO> produtoRanqueado = modelMapper.convertPage(produtos, ProdutoRanqueadoDTO.class);
		produtoRanqueado.forEach(conteudo -> {
			conteudo.setDataAtual(new Date());
			conteudo.setTermoPesquisado(termoPesquisado);
		});
		return ResponseEntity.ok().body(produtoRanqueado);
	}
}
