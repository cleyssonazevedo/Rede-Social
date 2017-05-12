package br.com.social.DAO.contato.email;

import org.springframework.stereotype.Repository;

import br.com.social.DAO.IGenericDAO;
import br.com.social.model.contato.Email;

@Repository
public interface IEmailDAO extends IGenericDAO<Long, Email> {

}
