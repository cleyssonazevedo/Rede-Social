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

import br.com.social.DAO.login.ILoginDAO;
import br.com.social.exception.UnauthorizedException;
import br.com.social.model.Login;
import br.com.social.model.contato.Contato;

@Controller
@RequestMapping("/login")
public class LoginController {
	@Autowired
	private ILoginDAO loginDAO;

	@RequestMapping(method = RequestMethod.POST)
	@ResponseBody
	protected Contato Logar(@RequestBody(required = true) Logon logon) throws UnauthorizedException, Exception {
		return loginDAO.Logar(new Login(logon.getUsuario(), logon.getSenha()));
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

class Logon {
	private String usuario;
	private String senha;

	public String getUsuario() {
		return usuario;
	}

	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}
}
