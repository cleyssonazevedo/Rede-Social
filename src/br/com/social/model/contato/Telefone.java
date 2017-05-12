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

@Entity
public class Telefone implements Serializable {
	private static final long serialVersionUID = -3203341311844235685L;

	public Telefone() {
		// TODO Auto-generated constructor stub
		this.contato = null;
		this.ddi = "0055";
	}

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@OnDelete(action = OnDeleteAction.CASCADE)
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "id_contato", updatable = false, foreignKey = @ForeignKey(name = "fk_telefone_contato"), nullable = false)
	private Contato contato;
	@Column(columnDefinition = "CHAR(4) NOT NULL DEFAULT '0055'")
	private String ddi;
	@Column(columnDefinition = "CHAR(2) NOT NULL")
	private String ddd;
	@Column(columnDefinition = "VARCHAR(25) NOT NULL")
	private String numero;
	@Column(columnDefinition = "ENUM('FIXO', 'CELULAR', 'COMERCIAL', 'FAX') NOT NULL")
	@Enumerated(EnumType.STRING)
	private TiposTelefones tipo;

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

	public String getDdi() {
		return ddi;
	}

	public void setDdi(String ddi) {
		this.ddi = ddi;
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

	public TiposTelefones getTipo() {
		return tipo;
	}

	public void setTipo(TiposTelefones tipo) {
		this.tipo = tipo;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

}

enum TiposTelefones {
	FIXO, COMERCIAL, CELULAR, RECADO, OUTROS;
}