package br.com.social.DAO.contato.endereco;

import org.springframework.stereotype.Component;

import br.com.social.DAO.GenericDAO;
import br.com.social.model.contato.Endereco;

@Component
public class EnderecoDAO extends GenericDAO<Long, Endereco> implements IEnderecoDAO {

	public EnderecoDAO() {
		// TODO Auto-generated constructor stub
		super(Endereco.class);
	}

}
