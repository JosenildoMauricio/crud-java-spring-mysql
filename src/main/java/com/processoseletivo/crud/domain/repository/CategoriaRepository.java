package com.processoseletivo.crud.domain.repository;

import com.processoseletivo.crud.domain.model.Categoria;
import com.processoseletivo.crud.domain.repository.filtro.CategoriaRepositoryQueries;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoriaRepository extends JpaRepository<Categoria, Long>, CategoriaRepositoryQueries {

    //
	
}
