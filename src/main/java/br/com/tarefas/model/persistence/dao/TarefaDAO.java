package br.com.tarefas.model.persistence.dao;

import br.com.tarefas.model.persistence.entity.Tarefa;
import br.com.tarefas.model.util.HibernateUtil;

public class TarefaDAO extends HibernateUtil{

	
	public Tarefa cadastrarTarefa(Tarefa tarefa) {
		try {
			beginTransaction();
			em.persist(tarefa);
			commitTransaction();
			return tarefa;
		} catch (Exception e) {
			rollbackTransaction();
			return null;
		}
	}

	public Tarefa atualizarListaTarefa(Tarefa tarefa) {
		try {
			beginTransaction();
			tarefa = em.merge(tarefa);
			commitTransaction();
			return tarefa;
		} catch (Exception e) {
			rollbackTransaction();
			return null;
		} 
	}
	
	public Tarefa findById(long id){
		try {
			return (Tarefa) em.find(Tarefa.class,id);
		} catch (Exception e) {
			return null;
		} 
	}
}
