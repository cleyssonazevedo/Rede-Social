package br.com.social.DAO.contato;

import org.springframework.stereotype.Component;

import br.com.social.DAO.GenericDAO;
import br.com.social.model.contato.Contato;

@Component
public class ContatoDAO extends GenericDAO<Long, Contato> implements IContatoDAO {
	public ContatoDAO() {
		// TODO Auto-generated constructor stub
		super(Contato.class);
	}
}
