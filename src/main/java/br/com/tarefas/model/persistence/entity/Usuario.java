package br.com.tarefas.model.persistence.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;

import lombok.Data;

@Entity
public @Data class Usuario {

	@Id
	@SequenceGenerator(name = "seq_usuario", initialValue = 1)
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;
	private String nome;
	private String email;
	private String senha;
	private String tokenFacebook;

}
