package br.com.social.controller.contato;

import javax.persistence.NoResultException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import br.com.social.DAO.Acesso;
import br.com.social.DAO.contato.IContatoDAO;
import br.com.social.DAO.contato.email.IEmailDAO;
import br.com.social.DAO.contato.endereco.IEnderecoDAO;
import br.com.social.DAO.contato.telefone.ITelefoneDAO;
import br.com.social.controller.ErrorHandler;
import br.com.social.exception.ConstraintViolationException;
import br.com.social.model.contato.Email;
import br.com.social.model.contato.Endereco;
import br.com.social.model.contato.Telefone;
import br.com.social.view.Contato;

@Controller
@RequestMapping("/contato")
public class ContatoController implements ErrorHandler {
	@Autowired
	private IContatoDAO contatoDAO;
	@Autowired
	private IEnderecoDAO enderecoDAO;
	@Autowired
	private IEmailDAO emailDAO;
	@Autowired
	private ITelefoneDAO telefoneDAO;
	
	/**
	 * Acesso publico
	 * @param contato
	 * @return
	 * @throws ConstraintViolationException
	 * @throws Exception
	 */
	@RequestMapping(method = RequestMethod.POST)
	@ResponseBody
	public Contato Cadastrar(@RequestBody Contato contato) throws ConstraintViolationException, Exception {
		br.com.social.model.contato.Contato dados = new br.com.social.model.contato.Contato();
		dados.setConta(contato.getConta());
		dados.setNome(contato.getNome());
		dados.setSobrenome(contato.getSobrenome());
		dados.setUsuario(contato.getUsuario());
		dados.setSenha(contato.getSenha());
		dados.setNascimento(contato.getNascimento());
		dados.setPerfil(contato.getPerfil());
		
		
		if(contatoDAO.ContaExiste(contato.getConta()))
			throw new ConstraintViolationException("Conta já existe!");
		
		if(contatoDAO.LoginExiste(contato.getUsuario()))
			throw new ConstraintViolationException("Usuário já existe!");
		
		if(contato.getEmails() == null || contato.getEmails().size() < 1)
			throw new ConstraintViolationException("Necessário Inserir um email!");
		
		contatoDAO.Inserir(dados);
		contato.setId(dados.getId());
		contato.setRegistro(dados.getRegistro());
		
		for(Email email : contato.getEmails()){
			email.setContato(dados);
			emailDAO.Inserir(email);
		}
		
		if(contato.getEnderecos() != null)
			for(Endereco endereco: contato.getEnderecos()){
				endereco.setContato(dados);
				enderecoDAO.Inserir(endereco);
			}
		
		
		if(contato.getTelefones() != null)
			for(Telefone telefone : contato.getTelefones()){
				telefone.setContato(dados);
				telefoneDAO.Inserir(telefone);
			}
		
		return contato;
	}
	
	/**
	 * Acesso publico
	 * @param conta
	 * @return
	 * @throws NoResultException
	 * @throws Exception
	 */
	@RequestMapping(value = "/{conta}")
	@ResponseBody
	@Cacheable
	public Contato BuscarPorConta(@PathVariable String conta) throws NoResultException, Exception {
		br.com.social.model.contato.Contato contato = contatoDAO.BuscarPorConta(conta, Acesso.PARCIAL);
		if(contato != null){
			Contato view = new Contato.Builder()
				.setId(contato.getId())
				.setNome(contato.getNome())
				.setSobrenome(contato.getSobrenome())
				.setConta(conta)
				.setRegistro(contato.getRegistro())
				.setNascimento(contato.getNascimento())
				.setPerfil(contato.getPerfil())
				.setEnderecos(enderecoDAO.Listar(contato.getId(), Acesso.PARCIAL))
				.setEmails(emailDAO.Listar(contato.getId(), Acesso.PARCIAL))
				.setTelefones(telefoneDAO.Listar(contato.getId(), Acesso.PARCIAL))
					.build();
			
			return view;
		} else
			throw new NoResultException();
	}
	
}
