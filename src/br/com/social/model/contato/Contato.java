package br.com.social.model.contato;

import java.io.Serializable;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.auth0.jwt.internal.com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonValue;

import br.com.social.model.contato.enums.Perfil;

@Entity
public class Contato implements Serializable  {
	@JsonIgnore
	public static final long serialVersionUID = 2753357407342092821L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(nullable = false, unique = true ,updatable = false)
	private String conta;

	@Column(nullable = false)
	private String nome;

	@Column(nullable = false)
	private String sobrenome;

	@Column(nullable = false, unique = true, length = 255)
	private String usuario;

	@Column(columnDefinition = "VARCHAR(255) BINARY NOT NULL")
	private String senha;

	@Temporal(TemporalType.DATE)
	@Column(nullable = false, updatable = false)
	private Date nascimento;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(nullable = false, updatable = false)
	private Date registro;

	@Enumerated(EnumType.STRING)
	@Column(columnDefinition = "ENUM('PUBLICO', 'PRIVADO') NOT NULL")
	private Perfil perfil;

	public Contato() {
		// TODO Auto-generated constructor stub
		registro = new Date();
	}

	public Contato(String usuario, String senha) {
		// TODO Auto-generated constructor stub
		this.usuario = usuario;
		this.senha = senha;
	}

	/* GETTERS and SETTERS */
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

	public void setSobrenome(String sobrenome) {
		this.sobrenome = sobrenome;
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

	public String getNascimento() {
		return new SimpleDateFormat("dd/MM/yyyy").format(nascimento);
	}

	public void setNascimento(String nascimento) {
		try {
			this.nascimento = new SimpleDateFormat("dd/MM/yyyy").parse(nascimento);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			this.nascimento = null;
		}
	}

	@JsonAnyGetter
	public String getRegistro() {
		return new SimpleDateFormat("dd/MM/yyyy hh:mm").format(registro);
	}

	public void setRegistro(String registro) {
		try {
			this.registro = new SimpleDateFormat("dd/MM/yyyy hh:mm").parse(registro);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			this.registro = null;
		}
	}

	@JsonValue
	public Perfil getPerfil() {
		return perfil;
	}

	public void setPerfil(Perfil perfil) {
		this.perfil = perfil;
	}

	public void setNascimento(Date nascimento) {
		this.nascimento = nascimento;
	}

	public void setRegistro(Date registro) {
		this.registro = registro;
	}

}