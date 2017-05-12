package br.com.social.DAO.contato.telefone;

import org.springframework.stereotype.Component;

import br.com.social.DAO.GenericDAO;
import br.com.social.model.contato.Telefone;

@Component
public class TelefoneDAO extends GenericDAO<Long, Telefone> implements ITelefoneDAO {
	public TelefoneDAO() {
		// TODO Auto-generated constructor stub
		super(Telefone.class);
	}
}
