package com.processoseletivo.crud.api.controller;

import com.processoseletivo.crud.domain.model.Categoria;
import com.processoseletivo.crud.domain.service.CategoriaService;
import jakarta.validation.Valid;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/categorias")
public class CategoriaController {

	@Autowired
	private CategoriaService categoriaService;

	@GetMapping
	public ResponseEntity<List<Categoria>> listarTodos() {
		return ResponseEntity
				.status(HttpStatus.OK)
				.body(categoriaService.listarTodos());
	}

	@GetMapping("/filtro-nome")
	public ResponseEntity<List<Categoria>> filtroNome(@RequestParam("nome") String nome) {
		return ResponseEntity
				.status(HttpStatus.OK)
				.body(categoriaService.filtroPorNome(nome));
	}

	@GetMapping("/{id}")
	public ResponseEntity<Categoria> buscarPorId(@PathVariable("id") Long id) {
		return ResponseEntity
				.status(HttpStatus.OK)
				.body(categoriaService.buscarPorId(id));
	}

	@PostMapping
	public ResponseEntity<Categoria> criar(@RequestBody @Valid Categoria categoria) {
		return ResponseEntity
				.status(HttpStatus.CREATED)
				.body(categoriaService.criar(categoria));
	}

	@PutMapping("/{id}")
	public ResponseEntity<Categoria> alterar(@PathVariable("id") Long id, @RequestBody @Valid Categoria categoria) {
		Categoria categoriaAtual = categoriaService.buscarPorId(id);

		BeanUtils.copyProperties(categoria, categoriaAtual, "id", "dataCadastro", "dataAtualizacao");

		return ResponseEntity
				.status(HttpStatus.OK)
				.body(categoriaService.alterar(categoria));

	}

	@DeleteMapping("/{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void deletar(@PathVariable("id") Long id) {
		Categoria categoria = categoriaService.buscarPorId(id);

		categoriaService.deletar(categoria);
	}
}
