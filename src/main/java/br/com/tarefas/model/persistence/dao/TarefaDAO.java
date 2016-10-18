package br.com.tarefas.model.persistence.dao;

import java.util.List;

import org.hibernate.criterion.Criterion;

import br.com.tarefas.model.persistence.entity.Tarefa;
import br.com.tarefas.model.util.HibernateUtil;

public class TarefaDAO extends HibernateUtil implements DAO<Tarefa>{

	
	public Tarefa cadastrar(Tarefa tarefa) {
		try {
			beginTransaction();
			em.persist(tarefa);
			commitTransaction();
			return em.merge(tarefa);
		} catch (Exception e) {
			rollbackTransaction();
			return null;
		}
	}

	public Tarefa atualizar(Tarefa tarefa) {
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
	
	public void remover(Tarefa tarefa){
		try{
			beginTransaction();
			em.remove(tarefa);
			commitTransaction();
		}catch(Exception e){
			rollbackTransaction();
		}
	}

	@Override
	public List<Tarefa> listar(Criterion... criterion) {
		return null;
	}


}
