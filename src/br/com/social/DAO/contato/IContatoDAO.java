package br.com.social.DAO.contato;

import org.springframework.stereotype.Repository;

import br.com.social.DAO.IGenericDAO;
import br.com.social.model.contato.Contato;

@Repository
public interface IContatoDAO extends IGenericDAO<Long, Contato> {
	
}
