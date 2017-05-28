package br.com.social.model.amigos;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ForeignKey;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import br.com.social.model.contato.Contato;

@Entity
public class Amigos {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "id_contato", foreignKey = @ForeignKey(name = "fk_amigos"), unique = true)
	private Contato contato;

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "id_amigo", foreignKey = @ForeignKey(name = "fk_amigos"), unique = true)
	private Contato amigo;

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

	public Contato getAmigo() {
		return amigo;
	}

	public void setAmigo(Contato amigo) {
		this.amigo = amigo;
	}

}
