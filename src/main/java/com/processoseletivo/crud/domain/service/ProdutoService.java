package com.processoseletivo.crud.domain.service;

import com.processoseletivo.crud.domain.exception.EntidadeNaoEncontradaException;
import com.processoseletivo.crud.domain.model.Produto;
import com.processoseletivo.crud.domain.repository.ProdutoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class ProdutoService {
	
	@Autowired
	private ProdutoRepository produtoRepository;
	
	public List<Produto> listarTodos() {
		return produtoRepository.findAll();
	}

	public List<Produto> listarPorNome(String nome) {
		return produtoRepository.filtroPorNomeProduto(nome);
	}

	public Produto buscarPorId(Long id) {
		return produtoRepository.findById(id)
				.orElseThrow(() -> new EntidadeNaoEncontradaException(
						String.format("Não existe um cadastro de Produto com ID %d", id)));
	}
	
	@Transactional
	public Produto criar(Produto produto) {
		return produtoRepository.save(produto);
	}

	@Transactional
	public Produto alterar(Produto produto) {
		return produtoRepository.save(produto);
	}
	
	@Transactional
	public void deletar(Produto produto) {
		try {
			produtoRepository.delete(produto.getId());
		} catch (EmptyResultDataAccessException e) {
			throw new EntidadeNaoEncontradaException(
					String.format("Não existe um cadastro de Produto com ID %d", produto.getId()));
		}
	}

}
