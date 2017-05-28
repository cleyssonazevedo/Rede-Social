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

import br.com.social.model.contato.enums.Estado;
import br.com.social.model.contato.enums.Perfil;
import br.com.social.model.contato.enums.TipoEndereco;

@Entity
public class Endereco implements Serializable {
	@JsonIgnore
	public static final long serialVersionUID = 3587275967215560319L;

	public Endereco() {
		// TODO Auto-generated constructor stub
		this.contato = null;
	}

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@JsonIgnore
	@OnDelete(action = OnDeleteAction.CASCADE)
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "id_contato", nullable = false, updatable = false, foreignKey = @ForeignKey(name = "fk_endereco_contato"))
	private Contato contato;
	
	@Column(nullable = false, updatable = false, length = 255)
	private String logradouro;
	
	@Column(nullable = false, updatable = false, length = 5)
	private String numero;
	
	@Column(nullable = false, updatable = false, length = 255)
	private String bairro;
	
	@Column(nullable = false, updatable = false, length = 255)
	private String cidade;
	
	@Column(columnDefinition = "ENUM('AC', 'AL', 'AM', 'AP', 'BA', 'CE', 'DF', 'ES', "
			+ "'GO', 'MA', 'MG', 'MS', 'MT', 'PA', 'PB', 'PE', 'PI', 'PR', 'RJ', 'RN', 'RO', "
			+ "'RR', 'RS', 'SC', 'SE', 'SP', 'TO') NOT NULL")
	@Enumerated(EnumType.STRING)
	private Estado estado;
	
	@Column(nullable = true, updatable = false, length = 255)
	private String complemento;
	
	@Column(columnDefinition = "CHAR(8) NOT NULL")
	private String cep;

	@Column(columnDefinition = "ENUM('RESIDENCIAL', 'COMERCIAL', 'OUTRO') NOT NULL")
	@Enumerated(EnumType.STRING)
	private TipoEndereco tipo;

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

	public String getLogradouro() {
		return logradouro;
	}

	public void setLogradouro(String logradouro) {
		this.logradouro = logradouro;
	}

	public String getNumero() {
		return numero;
	}

	public void setNumero(String numero) {
		this.numero = numero;
	}

	public String getBairro() {
		return bairro;
	}

	public void setBairro(String bairro) {
		this.bairro = bairro;
	}

	public String getCidade() {
		return cidade;
	}

	public void setCidade(String cidade) {
		this.cidade = cidade;
	}

	public Estado getEstado() {
		return estado;
	}

	public void setEstado(Estado estado) {
		this.estado = estado;
	}

	public String getComplemento() {
		return complemento;
	}

	public void setComplemento(String complemento) {
		this.complemento = complemento;
	}

	public String getCep() {
		return cep;
	}

	public void setCep(String cep) {
		this.cep = cep;
	}

	public TipoEndereco getTipo() {
		return tipo;
	}

	public void setTipo(TipoEndereco tipo) {
		this.tipo = tipo;
	}

	public Perfil getPerfil() {
		return perfil;
	}

	public void setPerfil(Perfil perfil) {
		this.perfil = perfil;
	}
}
