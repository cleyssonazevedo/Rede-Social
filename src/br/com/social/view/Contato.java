package br.com.social.view;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import com.auth0.jwt.internal.com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonGetter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import br.com.social.model.contato.Email;
import br.com.social.model.contato.Endereco;
import br.com.social.model.contato.Telefone;
import br.com.social.model.contato.enums.Perfil;

@JsonPropertyOrder(alphabetic = false)
public class Contato {

	private Long id;
	private String conta;
	private String nome;
	private String sobrenome;
	private String usuario;
	private String senha;
	private Date nascimento;
	private Date registro;
	private Perfil perfil;

	private List<Endereco> enderecos;
	private List<Email> emails;
	private List<Telefone> telefones;

	@JsonIgnore
	private static SimpleDateFormat data = new SimpleDateFormat("dd/MM/yyyy");
	@JsonIgnore
	private static SimpleDateFormat dataHora = new SimpleDateFormat("dd/MM/yyyy hh:mm");

	public Contato() {
		// TODO Auto-generated constructor stub
	}

	public Contato(Builder builder) {
		// TODO Auto-generated constructor stub
		id = builder.id;
		conta = builder.conta;
		nome = builder.nome;
		sobrenome = builder.sobrenome;
		usuario = builder.usuario;
		senha = builder.senha;
		nascimento = builder.nascimento;
		registro = builder.registro;
		perfil = builder.perfil;

		enderecos = builder.enderecos;
		emails = builder.emails;
		telefones = builder.telefones;
	}

	public static class Builder {
		private Long id;
		private String conta;
		private String nome;
		private String sobrenome;
		private String usuario;
		private String senha;
		private Date nascimento;
		private Date registro;
		private Perfil perfil;

		private List<Endereco> enderecos;
		private List<Email> emails;
		private List<Telefone> telefones;

		public Builder setId(Long id) {
			this.id = id;
			return this;
		}

		public Builder setConta(String conta) {
			this.conta = conta;
			return this;
		}

		public Builder setNome(String nome) {
			this.nome = nome;
			return this;
		}

		public Builder setSobrenome(String sobrenome) {
			this.sobrenome = sobrenome;
			return this;
		}

		public Builder setUsuario(String usuario) {
			this.usuario = usuario;
			return this;
		}

		public Builder setSenha(String senha) {
			this.senha = senha;
			return this;
		}

		public Builder setNascimento(Date nascimento) {
			this.nascimento = nascimento;
			return this;
		}

		public Builder setRegistro(Date registro) {
			this.registro = registro;
			return this;
		}

		public Builder setPerfil(Perfil perfil) {
			this.perfil = perfil;
			return this;
		}

		public Builder setEnderecos(List<Endereco> enderecos) {
			this.enderecos = enderecos;
			return this;
		}

		public Builder setEmails(List<Email> emails) {
			this.emails = emails;
			return this;
		}
		
		public Builder setNascimento(String nascimento) throws ParseException {
			this.nascimento = data.parse(nascimento);
			return this;
		}

		public Builder setRegistro(String registro) throws ParseException {
			this.registro = dataHora.parse(registro);
			return this;
		}

		public Builder setTelefones(List<Telefone> telefones) {
			this.telefones = telefones;
			return this;
		}
		
		public Contato build(){
			return new Contato(this);
		}
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getConta() {
		return conta;
	}

	public void setConta(String conta) {
		this.conta = conta;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getSobrenome() {
		return sobrenome;
	}

	public String getUsuario() {
		return usuario;
	}

	@JsonAnySetter
	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}

	public String getSenha() {
		return senha;
	}

	@JsonAnySetter
	public void setSenha(String senha) {
		this.senha = senha;
	}

	public void setSobrenome(String sobrenome) {
		this.sobrenome = sobrenome;
	}

	public String getNascimento() {
		return data.format(nascimento);
	}

	public void setNascimento(String nascimento) throws ParseException {
		this.nascimento = data.parse(nascimento);
	}

	@JsonGetter
	public String getRegistro() {
		return dataHora.format(registro);
	}

	public void setRegistro(String registro) throws ParseException {
		this.registro = dataHora.parse(registro);
	}

	public Perfil getPerfil() {
		return perfil;
	}

	public void setPerfil(Perfil perfil) {
		this.perfil = perfil;
	}

	public List<Endereco> getEnderecos() {
		return enderecos;
	}

	public void setEnderecos(List<Endereco> enderecos) {
		this.enderecos = enderecos;
	}

	public List<Email> getEmails() {
		return emails;
	}

	public void setEmails(List<Email> emails) {
		this.emails = emails;
	}

	public List<Telefone> getTelefones() {
		return telefones;
	}

	public void setTelefones(List<Telefone> telefones) {
		this.telefones = telefones;
	}
}