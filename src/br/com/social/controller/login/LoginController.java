package br.com.social.controller.login;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;

import br.com.social.DAO.Acesso;
import br.com.social.DAO.contato.IContatoDAO;
import br.com.social.DAO.contato.email.IEmailDAO;
import br.com.social.DAO.contato.endereco.IEnderecoDAO;
import br.com.social.DAO.contato.telefone.ITelefoneDAO;
import br.com.social.controller.ErrorHandler;
import br.com.social.exception.UnauthorizedException;
import br.com.social.view.Contato;
import br.com.social.view.Login;

@Controller
@RequestMapping("/login")
public class LoginController implements ErrorHandler {
	@Autowired
	private IContatoDAO contatoDAO;
	
	@Autowired
	private IEnderecoDAO enderecoDAO;
	
	@Autowired
	private IEmailDAO emailDAO;
	
	@Autowired
	private ITelefoneDAO telefoneDAO;

	@RequestMapping(method = RequestMethod.POST)
	protected ResponseEntity<Contato> Logar(@RequestBody(required = true) Login login) throws UnauthorizedException, Exception {	
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON_UTF8);
		headers.set("Authorization", login.getUsuario() + ":" + login.getSenha());
		
		br.com.social.model.contato.Contato model = contatoDAO.Logar(new br.com.social.model.contato.Contato(login.getUsuario(), login.getSenha()));
		
		Contato contato = new Contato.Builder()
				.setId(model.getId())
				.setConta(model.getConta())
				.setNome(model.getNome())
				.setUsuario(login.getUsuario())
				.setSenha(null)
				.setSobrenome(model.getSobrenome())
				.setNascimento(model.getNascimento())
				.setRegistro(model.getRegistro())
				.setPerfil(model.getPerfil())
				.setEnderecos(enderecoDAO.Listar(model.getId(), Acesso.TOTAL))
				.setEmails(emailDAO.Listar(model.getId(), Acesso.TOTAL))
				.setTelefones(telefoneDAO.Listar(model.getId(), Acesso.TOTAL))
					.build();
		
		return new ResponseEntity<Contato>(contato, headers, HttpStatus.OK);
	}

	@RequestMapping(method = RequestMethod.DELETE)
	@ResponseStatus(HttpStatus.OK)
	protected void ExcluirConta(@RequestBody(required = true) Login login) throws UnauthorizedException, Exception {
		contatoDAO.Remover(
			contatoDAO.Logar(
				new br.com.social.model.contato.Contato(
					login.getUsuario(),
					login.getSenha()
				)
			).getId()
		);
	}

}