package br.com.challenge.controller;

import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.httpBasic;
import static org.springframework.security.test.web.servlet.response.SecurityMockMvcResultMatchers.authenticated;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;

import com.fasterxml.jackson.databind.ObjectMapper;

import br.com.challenge.controller.dto.CategoriaProdutoDTO;
import br.com.challenge.controller.dto.ProdutoDTO;

@SpringBootTest
@AutoConfigureMockMvc
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class ProdutoControllerTests {

	@Autowired
	private MockMvc mockMvc;

	private ObjectMapper objectMapper;
	
	private final String BASE_URL = "/produto/";

	@BeforeAll
	public void beforeAll() {
		this.objectMapper = new ObjectMapper();
	}

	@Test
	public void method_save_unauthorized() throws Exception {
		this.mockMvc.perform(post(this.BASE_URL)).andExpect(status().isUnauthorized());
	}

	@Test
	public void method_update_unauthorized() throws Exception {
		this.mockMvc.perform(put(this.BASE_URL)).andExpect(status().isUnauthorized());
	}

	@Test
	public void method_delete_unauthorized() throws Exception {
		this.mockMvc.perform(delete(this.BASE_URL)).andExpect(status().isUnauthorized());
	}

	@Test
	public void method_save_authorized() throws Exception {
		this.mockMvc.perform(post(this.BASE_URL).with(httpBasic("admin", "teste"))).andExpect(authenticated());
	}

	@Test
	public void method_update_authorized() throws Exception {
		this.mockMvc.perform(put(this.BASE_URL).with(httpBasic("admin", "teste"))).andExpect(authenticated());
	}

	@Test
	public void method_delete_authorized() throws Exception {
		this.mockMvc.perform(delete(this.BASE_URL).with(httpBasic("admin", "teste"))).andExpect(authenticated());
	}

	@Test
	@WithMockUser(username = "admin", password = "teste", roles = "USER")
	public void method_save_nomenull() throws Exception {

		CategoriaProdutoDTO categoriaProdutoDTO = new CategoriaProdutoDTO();
		categoriaProdutoDTO.setId(1L);

		ProdutoDTO produtoDTO = new ProdutoDTO();
		produtoDTO.setNome(null);
		produtoDTO.setDescricao("produto teste");
		produtoDTO.setValorProduto(100.0D);
		produtoDTO.setCategoriaProduto(categoriaProdutoDTO);

		this.mockMvc
				.perform(post(this.BASE_URL).content(this.objectMapper.writeValueAsString(produtoDTO))
						.contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON))
				.andExpect(status().isBadRequest());

	}

}
