package br.com.social.DAO.contato;

import javax.persistence.NoResultException;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import br.com.social.DAO.IGenericDAO;
import br.com.social.exception.UnauthorizedException;
import br.com.social.model.contato.Contato;

@Repository
public interface IContatoDAO extends IGenericDAO<Long, Contato> {
	@Transactional(readOnly = true)
	Contato Logar(Contato contato) throws UnauthorizedException, Exception;

	Contato Outro(Contato contato) throws UnauthorizedException, Exception;

	@Transactional(readOnly = true)
	Contato BuscarPorConta(String conta) throws NoResultException, Exception;
	
	@Transactional(readOnly = true)
	boolean LoginExiste (String usuario);
	
	@Transactional(readOnly = true)
	boolean ContaExiste(String conta);
}
