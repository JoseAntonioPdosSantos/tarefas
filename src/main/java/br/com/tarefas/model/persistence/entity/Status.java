package br.com.tarefas.model.persistence.entity;

import lombok.Getter;
import lombok.Setter;

public enum Status {

	A_FAZER(0), FEITO(1);

	@Getter
	@Setter
	private int status;

	private Status(int status) {
		this.status = status;
	}
}
