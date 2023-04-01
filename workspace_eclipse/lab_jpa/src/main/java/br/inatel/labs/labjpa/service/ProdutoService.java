package br.inatel.labs.labjpa.service;

import java.util.List;

import org.springframework.stereotype.Service;

import br.inatel.labs.labjpa.entity.Produto;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;

@Service
@Transactional
public class ProdutoService {
	
	@PersistenceContext
	private EntityManager em;
	
	public Produto salvar(Produto p) {
		em.merge(p);
		return p;
	}
	
	public Produto buscarPeloId(Long id) {
		Produto produtoEncontrado = em.find(Produto.class, id);
		return produtoEncontrado;
	}
	
	public List<Produto> listar(){
		String jpql = "select p from Produto p";
		List<Produto> produtos = em.createQuery(jpql, Produto.class).getResultList();
		return produtos;
	}
	
	void remover(Produto p) {
		p = em.merge(p);
		em.remove(p);
	}
		
}
