package com.processoseletivo.crud.domain.repository.filtro;

import com.processoseletivo.crud.domain.model.Produto;

import java.util.List;

public interface ProdutoRepositoryQueries {

	List<Produto> filtroPorNomeProduto(String nome);

	void delete(Long id);

}