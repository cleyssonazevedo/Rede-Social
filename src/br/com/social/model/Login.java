package br.com.social.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ForeignKey;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import br.com.social.model.contato.Contato;

@Entity
public class Login implements Serializable {
	private static final long serialVersionUID = -3283776519331235055L;
	
	public Login() {
		// TODO Auto-generated constructor stub
	}
	
	public Login(String usuario, String senha) {
		// TODO Auto-generated constructor stub
		this.usuario = usuario;
		this.senha = senha;
		this.contato = null;
	}
	
	@Id
	@Column(columnDefinition = "VARCHAR(255) BINARY UNIQUE")
	private String usuario;
	@Column(columnDefinition = "VARCHAR(255) BINARY NOT NULL")
	private String senha;

	@OnDelete(action = OnDeleteAction.CASCADE)
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "id_contato", nullable = false, updatable = false, foreignKey = @ForeignKey(name = "fk_login_contato"))
	private Contato contato;

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

	public Contato getContato() {
		return contato;
	}

	public void setContato(Contato contato) {
		this.contato = contato;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

}
