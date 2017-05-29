package br.com.social.DAO.convite;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.NoResultException;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import org.springframework.stereotype.Component;

import br.com.social.DAO.GenericDAO;
import br.com.social.exception.ConstraintViolationException;
import br.com.social.model.convite.Convite;
import br.com.social.view.Convites;

@Component
public class ConviteDAO extends GenericDAO<Long, Convite> implements IConviteDAO {
	public ConviteDAO() {
		// TODO Auto-generated constructor stub
		super(Convite.class);
	}
	
	@Override
	public Convite Inserir(Convite convite) throws Exception {
		// TODO Auto-generated method stub
		if(convite.getDe().getId() != convite.getPara().getId())
			return super.Inserir(convite);
		else
			throw new ConstraintViolationException("Não pode se adicionar como amigo!");
	}

	@Override
	public List<Convites.Convite> Listar(Long id) throws NoResultException, Exception {
		// TODO Auto-generated method stub
		CriteriaBuilder builder = super.getManager().getCriteriaBuilder();
		CriteriaQuery<Convite> criteria = builder.createQuery(Convite.class);
		Root<Convite> root = criteria.from(Convite.class);
		
		criteria.select(root)
			.where(
				builder.equal(root.get("de"), id)
			);
		
		List<Convite> convites = super.getManager().createQuery(criteria).getResultList();
		
		Convites.Convite viewConvite = null;
		List<Convites.Convite> viewConvites = new ArrayList<>();
		Convites.Convite.Contato para = null;
		
		if(convites.isEmpty())
			throw new NoResultException();
		else
			for(Convite convite : convites) {
				viewConvite = new Convites.Convite();
				
				viewConvite.setId(convite.getId());
				viewConvite.setRegistro(convite.getRegistroDate());
				
				para = new Convites.Convite.Contato.Builder()
						.setNome(convite.getPara().getNome())
						.setSobrenome(convite.getPara().getSobrenome())
						.setConta(convite.getPara().getConta())
							.build();
				
				viewConvite.setPara(para);
				viewConvites.add(viewConvite);
			}
		
		return viewConvites;
	}
}