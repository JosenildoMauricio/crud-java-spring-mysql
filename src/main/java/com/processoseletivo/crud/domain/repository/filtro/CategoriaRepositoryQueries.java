package com.processoseletivo.crud.domain.repository.filtro;

import com.processoseletivo.crud.domain.model.Categoria;

import java.util.List;

public interface CategoriaRepositoryQueries {

	List<Categoria> filtroPorNome(String nome);
	
	void delete(Long id);

}