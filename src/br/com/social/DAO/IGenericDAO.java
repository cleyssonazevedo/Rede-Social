package br.com.social.DAO;

import java.io.Serializable;
import java.util.List;

import javax.persistence.NoResultException;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public interface IGenericDAO<ID extends Serializable, T> {
	/**
	 * M�todo padr�o para inser��o
	 * 
	 * @param modelo Objeto que ser� inserido
	 * @return Retorna o objeto inserida com o ID atualizado
	 * @throws Exception Em caso de erros em geral
	 */
	@Transactional
	T Inserir(T modelo) throws Exception;
	
	/**
	 * M�todo padr�o para altera��o
	 * @param modelo Objeto que ser� alterada
	 * @return O objeto alterado
	 * @throws Exception Em caso de erros em geral
	 */
	@Transactional
	T Alterar(T modelo) throws Exception;
	
	/**
	 * M�todo padr�o para dele��o
	 * @param modelo Objeto que ser� deletado
	 * @throws Exception Em caso de erros em geral
	 */
	@Transactional
	void Remover(T modelo) throws Exception;
	
	/**
	 * M�todo padr�o para busca de um objeto
	 * @param id Id do item no banco de dados
	 * @return O objeto com o ID
	 * @throws NoResultException Em caso de n�o ter o item com este ID
	 * @throws Exception Em caso de erro em geral
	 */
	@Transactional(readOnly = true)
	T Buscar(ID id) throws NoResultException, Exception;
	
	/**
	 * M�todo padr�o para busca de todos os objetos na tabela
	 * @return Lista com todos os objetos
	 * @throws NoResultException Caso n�o tenha itens no banco de dados
	 * @throws Exception Em caso de erros em geral
	 */
	@Transactional(readOnly = true)
	List<T> Listar() throws NoResultException, Exception;
	
}
