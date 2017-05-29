package br.com.social.DAO.convite;

import java.util.List;

import javax.persistence.NoResultException;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import br.com.social.DAO.IGenericDAO;
import br.com.social.model.convite.Convite;
import br.com.social.view.Convites;

@Repository
public interface IConviteDAO extends IGenericDAO<Long, Convite> {
	@Transactional
	Convite Inserir(Convite convite) throws Exception;
	
	@Transactional(readOnly = true)
	List<Convites.Convite> Listar(Long id) throws NoResultException, Exception;
}