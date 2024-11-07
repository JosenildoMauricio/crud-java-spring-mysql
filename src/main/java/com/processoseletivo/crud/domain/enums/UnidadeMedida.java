package com.processoseletivo.crud.domain.enums;

import lombok.Getter;

@Getter
public enum UnidadeMedida {

	KILOGRAMA("Kg"),
	METRO("Metro"),
	UNIDADE("Unidade"),
	PECA("Peça"),
	CAIXA("Caixa"),
	PAR("Par"),
	PACOTE("Pacote");

	private String descricao;

	private UnidadeMedida(String descricao) {
		this.descricao = descricao;
	}

}
