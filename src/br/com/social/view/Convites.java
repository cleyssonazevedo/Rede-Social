package br.com.social.view;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class Convites {
	private List<Convite> convites;

	public Convites() {
		// TODO Auto-generated constructor stub
	}
	
	public static class Convite {
		private Long id;
		private Date registro;
		private Contato para;

		public static class Contato {
			private String conta;
			private String nome;
			private String sobrenome;

			public Contato() {
				// TODO Auto-generated constructor stub
			}

			public Contato(Builder builder) {
				// TODO Auto-generated constructor stub
				conta = builder.conta;
				nome = builder.nome;
				sobrenome = builder.sobrenome;
			}

			public static class Builder {
				private String conta;
				private String nome;
				private String sobrenome;

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

				public Contato build() {
					return new Contato(this);
				}

			}

			public String getConta() {
				return conta;
			}

			public String getNome() {
				return nome;
			}

			public String getSobrenome() {
				return sobrenome;
			}

		}

		public Long getId() {
			return id;
		}

		public void setId(Long id) {
			this.id = id;
		}

		public String getRegistro() {
			return new SimpleDateFormat("dd/MM/yyyy hh:mm").format(registro);
		}

		public void setRegistro(Date registro) {
			this.registro = registro;
		}

		public Contato getPara() {
			return para;
		}

		public void setPara(Contato para) {
			this.para = para;
		}
	}

	public Convites(List<Convite> convites) {
		// TODO Auto-generated constructor stub
		this.convites = convites;
	}

	public List<Convite> getConvites() {
		return convites;
	}

	public void setConvites(List<Convite> convites) {
		this.convites = convites;
	}
	
	
}