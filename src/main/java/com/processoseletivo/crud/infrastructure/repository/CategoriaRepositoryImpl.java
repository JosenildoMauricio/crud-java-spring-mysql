package com.processoseletivo.crud.infrastructure.repository;

import com.processoseletivo.crud.domain.model.Categoria;
import com.processoseletivo.crud.domain.repository.filtro.CategoriaRepositoryQueries;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;
import jakarta.transaction.Transactional;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Repository;
import org.springframework.util.StringUtils;

import java.util.HashMap;
import java.util.List;

@Repository
public class CategoriaRepositoryImpl implements CategoriaRepositoryQueries {
	
	@PersistenceContext
    private EntityManager manager;

	@Override
	public List<Categoria> filtroPorNome(String nome) {

		var jpql = new StringBuilder();
		jpql.append("from Categoria where 0 = 0 ");

		var parametros = new HashMap<String, Object>();

		if(StringUtils.hasLength(nome)) {
			jpql.append("and nome like :nome ");
			parametros.put("nome", "%" + nome + "%");
		}

		TypedQuery<Categoria> query = manager.createQuery(jpql.toString(), Categoria.class);

		parametros.forEach((chave, valor) -> query.setParameter(chave, valor));

		return query.getResultList();
	}
    
    @Transactional
	@Override
	public void delete(Long id) {
		Categoria categoria = manager.find(Categoria.class, id);
		
		if (categoria == null) {
			throw new EmptyResultDataAccessException(1);
		}
		
		manager.remove(categoria);
	}

}
