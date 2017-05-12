package br.com.social.DAO.contato.endereco;

import org.springframework.stereotype.Repository;

import br.com.social.DAO.IGenericDAO;
import br.com.social.model.contato.Endereco;

@Repository
public interface IEnderecoDAO extends IGenericDAO<Long, Endereco> {

}
