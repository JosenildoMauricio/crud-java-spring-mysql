package com.processoseletivo.crud.api.controller;

import com.processoseletivo.crud.domain.model.Categoria;
import com.processoseletivo.crud.domain.model.Produto;
import com.processoseletivo.crud.domain.service.CategoriaService;
import com.processoseletivo.crud.domain.service.ProdutoService;
import jakarta.validation.Valid;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/produtos")
public class ProdutoController {

	@Autowired
	private ProdutoService produtoService;

	@Autowired
	private CategoriaService categoriaService;

	@GetMapping
	public ResponseEntity<List<Produto>> listarTodos() {
		return ResponseEntity
				.status(HttpStatus.OK)
				.body(produtoService.listarTodos());
	}

	@GetMapping("/filtro-nome")
	public ResponseEntity<List<Produto>> filtroNome(@RequestParam("nome") String nome) {
		return ResponseEntity
				.status(HttpStatus.OK)
				.body(produtoService.listarPorNome(nome));
	}

	@GetMapping("/{id}")
	public ResponseEntity<Produto> buscarPorId(@PathVariable("id") Long id) {
		return ResponseEntity
				.status(HttpStatus.OK)
				.body(produtoService.buscarPorId(id));
	}

	@PostMapping
	public ResponseEntity<Produto> criar(@RequestBody @Valid Produto produto) {
		Categoria categoria = categoriaService.buscarPorId(produto.getCategoria().getId());
		produto.setCategoria(categoria);
		return ResponseEntity
				.status(HttpStatus.CREATED)
				.body(produtoService.criar(produto));
	}

	@PutMapping("/{id}")
	public ResponseEntity<Produto> alterar(@PathVariable("id") Long id, @RequestBody @Valid Produto produto) {
		Produto produtoAtual = produtoService.buscarPorId(id);

		BeanUtils.copyProperties(produto, produtoAtual, "id", "dataCadastro", "dataAtualizacao");

		return ResponseEntity
				.status(HttpStatus.OK)
				.body(produtoService.alterar(produtoAtual));

	}

	@DeleteMapping("/{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void deletar(@PathVariable("id") Long id) {
		Produto produto = produtoService.buscarPorId(id);

		produtoService.deletar(produto);
	}
}
