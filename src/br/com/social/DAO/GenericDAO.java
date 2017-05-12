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
import org.springframework.transaction.annotation.Transactional;

@Component
public abstract class GenericDAO<ID extends Serializable, T> implements IGenericDAO<ID, T> {
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
	@Transactional
	public T Inserir(T modelo) throws Exception {
		// TODO Auto-generated method stub
		try {
			this.getManager().persist(modelo);
			return modelo;
		} catch (Exception e) {
			// TODO: handle exception
			throw e;
		}
	}

	@Override
	@Transactional
	public T Alterar(T modelo) throws Exception {
		// TODO Auto-generated method stub
		try {
			this.getManager().merge(modelo);
			return modelo;
		} catch (Exception e) {
			// TODO: handle exception
			throw e;
		}
	}

	@Override
	@Transactional
	public void Remover(T modelo) throws Exception {
		// TODO Auto-generated method stub
		try {
			this.getManager().remove(modelo);
		} catch (Exception e) {
			// TODO: handle exception
			throw e;
		}
	}

	@Override
	@Transactional(readOnly = true)
	public T Buscar(ID id) throws NoResultException, Exception {
		// TODO Auto-generated method stub
		try {
			return this.getManager().find(getPersistenceClass(), id);
		} catch (Exception e) {
			// TODO: handle exception
			throw e;
		}
	}

	@Override
	@Transactional(readOnly = true)
	public List<T> Listar() throws NoResultException, Exception {
		// TODO Auto-generated method stub
		try {
			CriteriaBuilder builder = this.getManager().getCriteriaBuilder();
			CriteriaQuery<T> criteria = builder.createQuery(getPersistenceClass());
			Root<T> root = criteria.from(this.getPersistenceClass());
			criteria.select(root);
			TypedQuery<T> query = this.getManager().createQuery(criteria);

			return query.getResultList();
		} catch (Exception e) {
			// TODO: handle exception
			throw e;
		}
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