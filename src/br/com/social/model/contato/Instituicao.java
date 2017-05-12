package br.com.social.model.contato;

import java.io.Serializable;

import javax.persistence.Entity;
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
public class Instituicao implements Serializable {
	private static final long serialVersionUID = -468224124983091110L;
	
	public Instituicao() {
		// TODO Auto-generated constructor stub
		this.contato = null;
	}
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@OnDelete(action = OnDeleteAction.CASCADE)
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "id_contato", foreignKey = @ForeignKey(name = "fk_instituicao_contato"), nullable = false, updatable = false)
	private Contato contato;
}
