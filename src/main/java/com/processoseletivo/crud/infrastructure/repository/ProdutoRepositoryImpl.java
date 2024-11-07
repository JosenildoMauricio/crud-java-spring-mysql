package com.processoseletivo.crud.infrastructure.repository;

import com.processoseletivo.crud.domain.model.Produto;
import com.processoseletivo.crud.domain.repository.filtro.ProdutoRepositoryQueries;
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
public class ProdutoRepositoryImpl implements ProdutoRepositoryQueries {
	
	@PersistenceContext
    private EntityManager manager;

	@Override
	public List<Produto> filtroPorNomeProduto(String nome) {

		var jpql = new StringBuilder();
		jpql.append("from Produto where 0 = 0 ");

		var parametros = new HashMap<String, Object>();

		if(StringUtils.hasLength(nome)) {
			jpql.append("and nome like :nome ");
			parametros.put("nome", "%" + nome + "%");
		}

		TypedQuery<Produto> query = manager.createQuery(jpql.toString(), Produto.class);

		parametros.forEach((chave, valor) -> query.setParameter(chave, valor));

		return query.getResultList();
	}
    
    @Transactional
	@Override
	public void delete(Long id) {
		Produto produto = manager.find(Produto.class, id);
		
		if (produto == null) {
			throw new EmptyResultDataAccessException(1);
		}
		
		manager.remove(produto);
	}

}
