package br.com.social.DAO;

import java.io.Serializable;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import org.springframework.stereotype.Component;

import br.com.social.model.contato.enums.Perfil;

@Component
public abstract class GenericDAO<ID extends Serializable, T> implements IGenericDAO<ID, T>, IGenericContato<ID, T> {
	private Class<T> persistenceClass;
	@PersistenceContext
	private EntityManager manager;

	/**
	 * Construtor com o objetivo de informar qual classe será gravado no banco
	 * de dados
	 * 
	 * @param persistenceClass
	 *            Classe.class, classe que será gravado no banco
	 */
	public GenericDAO(Class<T> persistenceClass) {
		// TODO Auto-generated constructor stub
		this.persistenceClass = persistenceClass;
	}

	@Override
	public T Inserir(T modelo) throws Exception {
		// TODO Auto-generated method stub
		getManager().persist(modelo);
		return modelo;
	}

	@Override
	public T Alterar(T modelo) throws Exception {
		// TODO Auto-generated method stub
		getManager().merge(modelo);
		return modelo;
	}

	@Override
	public void Remover(T modelo) throws Exception {
		// TODO Auto-generated method stub
		getManager().remove(modelo);
	}

	@Override
	public T Buscar(ID id) throws NoResultException, Exception {
		// TODO Auto-generated method stub
		return getManager().find(getPersistenceClass(), id);
	}

	@Override
	public List<T> Listar() throws NoResultException, Exception {
		// TODO Auto-generated method stub
		try {
			CriteriaBuilder builder = getManager().getCriteriaBuilder();
			CriteriaQuery<T> criteria = builder.createQuery(getPersistenceClass());
			Root<T> root = criteria.from(getPersistenceClass());
			criteria.select(root);
			TypedQuery<T> query = getManager().createQuery(criteria);
			
			if (query.getResultList().isEmpty())
				return query.getResultList();
			else
				return null;
			
		} catch (Exception e) {
			// TODO: handle exception
			throw e;
		}
	}

	@Override
	public List<T> Listar(ID id) throws NoResultException, Exception {
		// TODO Auto-generated method stub
		CriteriaBuilder builder = getManager().getCriteriaBuilder();
		CriteriaQuery<T> criteria = builder.createQuery(getPersistenceClass());
		Root<T> root = criteria.from(getPersistenceClass());
		
		criteria.select(root)
			.where(
				builder.and(
						builder.equal(root.get("contato"), id),
						builder.equal(root.get("perfil"), Perfil.PUBLICO)
				)
			);
		
		List<T> query = getManager().createQuery(criteria).getResultList();
		if(!query.isEmpty())
			return query;
		else
			return null;
	}

	// Getter And Setters
	public Class<T> getPersistenceClass() {
		return persistenceClass;
	}

	public EntityManager getManager() {
		return manager;
	}

	public void setManager(EntityManager manager) {
		this.manager = manager;
	}

}