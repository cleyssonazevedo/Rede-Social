package br.com.social.model.contato.enums;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;

@JsonSerialize
public enum TipoEmail {
	PESSOAL, COMERCIAL, OUTROS;
}
