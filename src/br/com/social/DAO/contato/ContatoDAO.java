package br.com.social.DAO.contato;

import javax.persistence.NoResultException;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Path;
import javax.persistence.criteria.Root;

import org.springframework.stereotype.Component;

import br.com.social.DAO.Acesso;
import br.com.social.DAO.GenericDAO;
import br.com.social.exception.UnauthorizedException;
import br.com.social.model.contato.Contato;
import br.com.social.model.contato.enums.Perfil;

@Component
public class ContatoDAO extends GenericDAO<Long, Contato> implements IContatoDAO {
	public ContatoDAO() {
		// TODO Auto-generated constructor stub
		super(Contato.class);
	}

	@Override
	public Contato Logar(Contato contato) throws UnauthorizedException, Exception {
		// TODO Auto-generated method stub
		try {
			CriteriaBuilder builder = super.getManager().getCriteriaBuilder();
			CriteriaQuery<Contato> criteria = builder.createQuery(Contato.class);
			Root<Contato> root = criteria.from(Contato.class);
			
			criteria.select(root)
				.where(
					builder.and(
						builder.equal(root.get("usuario"), contato.getUsuario()),
						builder.equal(root.get("senha"), contato.getSenha())
					)
				);
			
			return super.getManager().createQuery(criteria).getSingleResult();
		} catch (NoResultException e) {
			// TODO: handle exception
			throw new UnauthorizedException(e);
		} catch (Exception e) {
			// TODO: handle exception
			throw e;
		}
	}

	@Override
	public Contato Outro(Contato contato) throws UnauthorizedException, Exception {
		// TODO Auto-generated method stub
		contato.setId((long) 1);
		contato.setConta("cleysson12");
		contato.setNome("Cleysson");
		contato.setSobrenome("Azevedo");
		contato.setPerfil(Perfil.PUBLICO);
		contato.setNascimento("08/04/1994");
		return contato;
	}

	@Override
	public Contato BuscarPorConta(String conta, Acesso acesso) throws NoResultException, Exception {
		// TODO Auto-generated method stub
		CriteriaBuilder builder = super.getManager().getCriteriaBuilder();
		CriteriaQuery<Contato> criteria = builder.createQuery(Contato.class);
		Root<Contato> root = criteria.from(Contato.class);
		
		if(acesso.equals(Acesso.PARCIAL)){
			criteria.select(root)
			.where(
				builder.and(
					builder.equal(root.get("conta"), conta),
					builder.equal(root.get("perfil"), Perfil.PUBLICO)
				)
			);
		} else {
			criteria.select(root)
			.where(
				builder.equal(root.get("conta"), conta)
			);
		}
		
		return super.getManager().createQuery(criteria).getSingleResult();
	}

	@Override
	public boolean LoginExiste (String usuario) {
		try {
			CriteriaBuilder builder = super.getManager().getCriteriaBuilder();
			CriteriaQuery<Contato> criteria = builder.createQuery(Contato.class);
			Root<Contato> root = criteria.from(Contato.class);
			Path<String> path = root.get("usuario");
			
			criteria.select(root)
				.where(builder.equal(path, usuario));
				
			if(super.getManager().createQuery(criteria)
					.getSingleResult() != null)
				return true;
			else
				return false;
		} catch (Exception e) {
			// TODO: handle exception
			return false;
		}
	}

	@Override
	public boolean ContaExiste(String conta) {
		try {
			CriteriaBuilder builder = super.getManager().getCriteriaBuilder();
			CriteriaQuery<Contato> criteria = builder.createQuery(Contato.class);
			Root<Contato> root = criteria.from(Contato.class);
			Path<String> path = root.get("conta");
			
			criteria.select(root)
				.where(
					builder.equal(path, conta)	
				);
			
			if(super.getManager().createQuery(criteria)
					.getSingleResult() != null)
				return true;
			else
				return false;
		} catch (Exception e) {
			// TODO: handle exception
			return false;
		}
	}
	
}
