package com.processoseletivo.crud.domain.service;

import com.processoseletivo.crud.domain.exception.EntidadeEmUsoException;
import com.processoseletivo.crud.domain.exception.EntidadeNaoEncontradaException;
import com.processoseletivo.crud.domain.exception.NegocioException;
import com.processoseletivo.crud.domain.model.Categoria;
import com.processoseletivo.crud.domain.repository.CategoriaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class CategoriaService {
	
	@Autowired
	private CategoriaRepository categoriaRepository;
	
	public List<Categoria> listarTodos() {
		List<Categoria> categorias = categoriaRepository.findAll();
		return categorias;
	}

	public List<Categoria> filtroPorNome(String nome) {
		return categoriaRepository.filtroPorNome(nome);
	}

	public Categoria buscarPorId(Long id) {
		return categoriaRepository.findById(id)
				.orElseThrow(() -> new EntidadeNaoEncontradaException(
						String.format("Não existe um cadastro de Categoria com ID %d", id)));
	}

	@Transactional
	public Categoria criar(Categoria categoria) {
		try {
			return categoriaRepository.save(categoria);
		} catch (DataIntegrityViolationException e) {
			throw new NegocioException(
					String.format("Já existe uma Categoria com este nome.", categoria.getNome()));
		}
	}

	@Transactional
	public Categoria alterar(Categoria categoria) {
		return categoriaRepository.save(categoria);
	}
	
	@Transactional
	public void deletar(Categoria categoria) {
		try {
			categoriaRepository.delete(categoria.getId());
		} catch (EmptyResultDataAccessException e) {
			throw new EntidadeNaoEncontradaException(
					String.format("Não existe um cadastro de Categoria com ID %d", categoria.getId()));
			
		} catch (DataIntegrityViolationException e) {
			throw new EntidadeEmUsoException(
					String.format("A Categoria de ID %d não pode ser removida, pois está em uso.", categoria.getId()));
		}
		
	}

}
