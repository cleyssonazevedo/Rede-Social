package br.com.social.DAO.contato.email;

import org.springframework.stereotype.Component;

import br.com.social.DAO.GenericDAO;
import br.com.social.model.contato.Email;

@Component
public class EmailDAO extends GenericDAO<Long, Email> implements IEmailDAO {
	public EmailDAO() {
		// TODO Auto-generated constructor stub
		super(Email.class);
	}
}
