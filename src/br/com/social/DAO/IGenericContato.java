package br.com.social.DAO;

import java.util.List;

import javax.persistence.NoResultException;

import org.springframework.transaction.annotation.Transactional;

/**
 * Métodos somente utilizaveis pelas classes que tem id de contato
 */
public interface IGenericContato <ID, T> {
	/**
	 * Método padrão para busca de todos os objetos na tabela com um id
	 * @param id Id da tabela principal
	 * @return Lista com todos os objetos
	 * @throws NoResultException Caso não tenha itens no banco de dados
	 * @throws Exception Em caso de erros em geral
	 */
	@Transactional(readOnly = true)
	List<T> Listar(ID id) throws NoResultException, Exception;
}
