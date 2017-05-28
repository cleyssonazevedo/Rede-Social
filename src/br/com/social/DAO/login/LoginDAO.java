package br.com.social.DAO.login;

/**
 * 
import javax.persistence.NoResultException;
import javax.persistence.Tuple;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Path;
import javax.persistence.criteria.Root;

import org.springframework.stereotype.Component;

import br.com.social.DAO.GenericDAO;
import br.com.social.exception.UnauthorizedException;
import br.com.social.model.Login;
import br.com.social.model.contato.Contato;

public class LoginDAO extends GenericDAO<Long, Login> implements ILoginDAO {

	public LoginDAO() {
		// TODO Auto-generated constructor stub
		super(Login.class);
	}

	@Override
	public Contato Logar(Login login) throws UnauthorizedException, Exception {
		// TODO Auto-generated method stub
		try {
			CriteriaBuilder builder = super.getManager().getCriteriaBuilder();
			CriteriaQuery<Tuple> criteria = builder.createTupleQuery();
			Root<Login> root = criteria.from(Login.class);

			Path<Contato> contato = root.get("contato");
			contato.alias("id_contato");

			criteria.where(builder.and(builder.equal(root.get("usuario"), login.getUsuario()),
					builder.equal(root.get("senha"), login.getSenha())));

			criteria.multiselect(contato);

			Tuple result = super.getManager().createQuery(criteria).getSingleResult();
			if (result != null)
				return result.get(contato);
			else
				throw new UnauthorizedException();
		} catch (NoResultException ue) {
			// TODO: handle exception
			throw new UnauthorizedException();
		} catch (Exception e) {
			// TODO: handle exception
			throw e;
		}
	}

}

*/
 public class LoginDAO {
	 
 }
