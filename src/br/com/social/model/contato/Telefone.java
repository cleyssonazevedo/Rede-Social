package br.com.social.model.contato;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.ForeignKey;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import com.fasterxml.jackson.annotation.JsonIgnore;

import br.com.social.model.contato.enums.Perfil;
import br.com.social.model.contato.enums.TipoTelefone;

@Entity
public class Telefone implements Serializable {
	@JsonIgnore
	public static final long serialVersionUID = -3203341311844235685L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@JsonIgnore
	@OnDelete(action = OnDeleteAction.CASCADE)
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "id_contato", updatable = false, foreignKey = @ForeignKey(name = "fk_telefone_contato"), nullable = false)
	private Contato contato;
	
	@Column(columnDefinition = "CHAR(2) NOT NULL")
	private String ddd;
	
	@Column(nullable = false, length = 25)
	private String numero;
	
	@Column(columnDefinition = "ENUM('FIXO', 'CELULAR', 'COMERCIAL', 'OUTROS') NOT NULL")
	@Enumerated(EnumType.STRING)
	private TipoTelefone tipo;

	@Enumerated(EnumType.STRING)
	@Column(columnDefinition = "ENUM('PUBLICO', 'PRIVADO') NOT NULL")
	private Perfil perfil;
	
	
	public Telefone() {
		// TODO Auto-generated constructor stub
		this.contato = null;
	}

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

	public String getDdd() {
		return ddd;
	}

	public void setDdd(String ddd) {
		this.ddd = ddd;
	}

	public String getNumero() {
		return numero;
	}

	public void setNumero(String numero) {
		this.numero = numero;
	}

	public TipoTelefone getTipo() {
		return tipo;
	}

	public void setTipo(TipoTelefone tipo) {
		this.tipo = tipo;
	}

	public Perfil getPerfil() {
		return perfil;
	}

	public void setPerfil(Perfil perfil) {
		this.perfil = perfil;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

}