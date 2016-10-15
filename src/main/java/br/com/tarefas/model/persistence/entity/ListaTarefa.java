package br.com.tarefas.model.persistence.entity;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;

import lombok.Data;

@Entity
public @Data class ListaTarefa {

	@Id
	@SequenceGenerator(name = "seq_lista_tarefa", initialValue = 1)
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;
	private String nome;
	@OneToOne
	@JoinColumn
	private Cor cor;
	@ManyToOne
	@JoinColumn
	private Usuario usuario;
	@OneToMany
	@JoinColumn
	private List<Tarefa> tarefas;
	
}
