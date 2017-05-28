package br.com.social.model.contato.enums;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;

@JsonSerialize
public enum Estado {
	AC("Acre"),
	AL("Alagoas"),
	AP("Amapá"),
	AM("Amazonas"),
	BA("Bahia"),
	CE("Ceará"),
	DF("Distrito Federal"),
	ES("Espírito Santo"),
	GO("Goiás"),
	MA("Maranhão"),
	MT("Mato Grosso"),
	MS("Mato Grosso do Sul"),
	MG("Minas Gerais"),
	PA("Pará"),
	PB("Paraíba"),
	PR("Paraná"), 
	PE("Pernambuco"), 
	PI("Piauí"), 
	RR("Roraima"),
	RO("Rondônia"), 
	RJ("Rio de Janeiro"), 
	RN("Rio Grande do Norte"), 
	RS("Rio Grande do Sul"), 
	SC("Santa Catarina"), 
	SP("São Paulo"), 
	SE("Sergipe"); 
	
	private final String nome;
	
	private Estado(String nome) {
		// TODO Auto-generated constructor stub
		this.nome = nome;
	}
	
	public String getNome(){
		return nome;
	}
}
