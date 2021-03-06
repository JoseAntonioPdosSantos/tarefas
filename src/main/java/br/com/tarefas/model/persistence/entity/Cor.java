package br.com.tarefas.model.persistence.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Entity
@EqualsAndHashCode(exclude="nomeCor")
public @Data class Cor {

	@Id
	@SequenceGenerator(name = "seq_cor", initialValue = 1)
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;
	private String nomeCor;
}
