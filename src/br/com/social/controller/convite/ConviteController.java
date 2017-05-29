package br.com.social.controller.convite;

import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import br.com.social.DAO.Acesso;
import br.com.social.DAO.contato.IContatoDAO;
import br.com.social.DAO.convite.IConviteDAO;
import br.com.social.controller.ErrorHandler;
import br.com.social.exception.ConstraintViolationException;
import br.com.social.exception.UnauthorizedException;
import br.com.social.model.convite.Convite;
import br.com.social.view.Convites;

@Controller
@RequestMapping("/convite")
public class ConviteController implements ErrorHandler {
	HttpHeaders headers = new HttpHeaders();
	
	@Autowired
	private IContatoDAO contatoDAO;
	
	@Autowired
	private IConviteDAO conviteDAO;
	
	public ConviteController() {
		// TODO Auto-generated constructor stub
		headers.setContentType(MediaType.APPLICATION_JSON_UTF8);
	}

	@RequestMapping(method = RequestMethod.POST)
	protected ResponseEntity<String> EnviarConvite(@RequestBody(required = true) String body,
			@RequestHeader(name = "Authorization", required = true) String authorization)
			throws JSONException, ConstraintViolationException, Exception {
		JSONObject json = new JSONObject(body);
		if (authorization != null) {
			Convite convite = new Convite.Builder()
				.setDe(contatoDAO.BuscarPorConta(json.getString("de"), Acesso.TOTAL))
				.setPara(contatoDAO.BuscarPorConta(json.getString("para"), Acesso.TOTAL))
					.build();
			
			conviteDAO.Inserir(convite);
			
			JSONObject response = new JSONObject();
			response.put("id", convite.getId());
			
			return new ResponseEntity<>(response.toString(), headers, HttpStatus.CREATED);
		} else
			throw new UnauthorizedException();
	}

	@RequestMapping(value = "/{id}",method = RequestMethod.DELETE)
	@ResponseStatus(HttpStatus.OK)
	protected void RemoverConvite(@PathVariable Long id,
			@RequestHeader(name = "Authorization", required = true) String authorization)
			throws JSONException, ConstraintViolationException, Exception {
		
		if(authorization != null) {
			conviteDAO.Remover(id);
		} else
			throw new UnauthorizedException();
	}

	
	/**
	 * Alterar mais tarde para buscar de acordo com o id do authorization
	 * @param id
	 * @return
	 * @throws Exception 
	 */
	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	@ResponseBody
	protected Convites ListarConvites(@PathVariable Long id) throws Exception {
		return new Convites(conviteDAO.Listar(id));
	}
}