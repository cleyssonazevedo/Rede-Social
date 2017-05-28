package br.com.social.controller.login;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import br.com.social.DAO.contato.IContatoDAO;
import br.com.social.exception.UnauthorizedException;
import br.com.social.model.contato.Contato;
import br.com.social.view.Login;

@Controller
@RequestMapping("/login")
public class LoginController {
	@Autowired
	private IContatoDAO contatoDAO;

	@RequestMapping(method = RequestMethod.POST)
	@ResponseBody
	protected Contato Logar(@RequestBody(required = true) Login login) throws UnauthorizedException, Exception {	
		return contatoDAO.Logar(new Contato(login.getUsuario(), login.getSenha()));
	}

	@ExceptionHandler(UnauthorizedException.class)
	@ResponseStatus(HttpStatus.UNAUTHORIZED)
	private void UnauthorizedHandler(UnauthorizedException e) {
	}

	@ExceptionHandler(Exception.class)
	@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
	private void ExceptionHandlerError(Exception e) {
		e.printStackTrace();
	}
}
