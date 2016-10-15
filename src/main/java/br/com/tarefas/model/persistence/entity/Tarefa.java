package br.com.tarefas.model.persistence.entity;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Entity
@EqualsAndHashCode(exclude={"listaTarefa","descricao","status"})
public @Data class Tarefa {

	@Id
	@SequenceGenerator(name = "seq_tarefa", initialValue = 1)
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;
	@ManyToOne
	@JoinColumn
	private ListaTarefa listaTarefa;
	private String descricao;
	@Enumerated(EnumType.ORDINAL)
	private Status status;
}
