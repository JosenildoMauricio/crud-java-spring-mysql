package com.processoseletivo.crud.domain.repository;

import com.processoseletivo.crud.domain.model.Produto;
import com.processoseletivo.crud.domain.repository.filtro.ProdutoRepositoryQueries;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProdutoRepository extends JpaRepository<Produto, Long>, ProdutoRepositoryQueries {

    //

}
