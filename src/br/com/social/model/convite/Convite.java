package br.com.social.model.convite;

import java.io.Serializable;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ForeignKey;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.UniqueConstraint;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import com.fasterxml.jackson.annotation.JsonGetter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import br.com.social.model.contato.Contato;

@Entity
@Table(uniqueConstraints = @UniqueConstraint(name = "de_para", columnNames = { "id_contato_de", "id_contato_para" }))
@JsonPropertyOrder(value =  {"id", "registro", "de", "para"})
public class Convite implements Serializable {
	@JsonIgnore
	public static final long serialVersionUID = 7870101466245636888L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@OnDelete(action = OnDeleteAction.CASCADE)
	@ManyToOne
	@JoinColumn(name = "id_contato_de", nullable = false, updatable = false, foreignKey = @ForeignKey(name = "fk_contato_convite_de"))
	private Contato de;

	@OnDelete(action = OnDeleteAction.CASCADE)
	@ManyToOne
	@JoinColumn(name = "id_contato_para", nullable = false, updatable = false, foreignKey = @ForeignKey(name = "fk_contato_convite_para"))
	private Contato para;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(nullable = false, updatable = false)
	private Date registro;

	public Convite() {
		// TODO Auto-generated constructor stub
		this.registro = new Date();
	}

	public Convite(Builder builder) {
		// TODO Auto-generated constructor stub
		id = builder.id;
		de = builder.de;
		para = builder.para;
		registro = builder.registro;

	}

	public static class Builder {
		private Long id;
		private Contato de;
		private Contato para;
		private Date registro = new Date();

		public Builder setID(Long id) {
			this.id = id;
			return this;
		}

		public Builder setDe(Contato de) {
			this.de = de;
			return this;
		}

		public Builder setPara(Contato para) {
			this.para = para;
			return this;
		}

		public Builder setRegistro(String registro) throws ParseException {
			this.registro = new SimpleDateFormat("dd/MM/yyyy").parse(registro);
			return this;
		}

		public Convite build() {
			return new Convite(this);
		}
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Contato getDe() {
		return de;
	}

	public void setDe(Contato de) {
		this.de = de;
	}

	public Contato getPara() {
		return para;
	}

	public void setPara(Contato para) {
		this.para = para;
	}
	
	public void setRegistro(Date registro) {
		this.registro = registro;
	}

	@JsonGetter
	public String getRegistro() {
		return new SimpleDateFormat("dd/MM/yyyy hh:mm").format(registro);
	}
	
	public Date getRegistroDate(){
		return registro;
	}
}