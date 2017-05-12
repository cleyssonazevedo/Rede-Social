package br.com.social.DAO.login;

import org.springframework.stereotype.Repository;

import br.com.social.DAO.IGenericDAO;
import br.com.social.exception.UnauthorizedException;
import br.com.social.model.Login;
import br.com.social.model.contato.Contato;

@Repository
public interface ILoginDAO extends IGenericDAO<Long, Login> {
	Contato Logar(Login login) throws UnauthorizedException, Exception;
}
