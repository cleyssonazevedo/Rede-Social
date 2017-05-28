package br.com.social.model.contato;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.ForeignKey;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import com.fasterxml.jackson.annotation.JsonIgnore;

import br.com.social.model.contato.enums.Perfil;
import br.com.social.model.contato.enums.TipoEmail;

@Entity
public class Email implements Serializable {
	@JsonIgnore
	public static final long serialVersionUID = -7741393511974698310L;

	public Email() {
		// TODO Auto-generated constructor stub
		this.contato = null;
	}

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@JsonIgnore
	@OnDelete(action = OnDeleteAction.CASCADE)
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "id_cliente", updatable = false, foreignKey = @ForeignKey(name = "fk_email_contato"), nullable = false)
	private Contato contato;
	
	@Column(nullable = false, updatable = false, length = 255)
	private String email;
	
	@Column(columnDefinition = "ENUM('PESSOAL', 'COMERCIAL', 'OUTROS') NOT NULL")
	@Enumerated(EnumType.STRING)
	private TipoEmail tipo;
	
	@Enumerated(EnumType.STRING)
	@Column(columnDefinition = "ENUM('PUBLICO', 'PRIVADO') NOT NULL")
	private Perfil perfil;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Contato getContato() {
		return contato;
	}

	public void setContato(Contato contato) {
		this.contato = contato;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public TipoEmail getTipo() {
		return tipo;
	}

	public void setTipo(TipoEmail tipo) {
		this.tipo = tipo;
	}

	public Perfil getPerfil() {
		return perfil;
	}

	public void setPerfil(Perfil perfil) {
		this.perfil = perfil;
	}
}
