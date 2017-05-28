package br.com.social.DAO.contato.telefone;

import org.springframework.stereotype.Repository;

import br.com.social.DAO.IGenericContato;
import br.com.social.DAO.IGenericDAO;
import br.com.social.model.contato.Telefone;

@Repository
public interface ITelefoneDAO extends IGenericDAO<Long, Telefone>, IGenericContato<Long, Telefone> {

}
