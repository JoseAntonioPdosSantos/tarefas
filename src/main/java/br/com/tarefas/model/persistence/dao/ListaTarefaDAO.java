package br.com.tarefas.model.persistence.dao;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Criterion;

import br.com.tarefas.model.persistence.entity.ListaTarefa;
import br.com.tarefas.model.persistence.entity.Tarefa;
import br.com.tarefas.model.util.HibernateUtil;

public class ListaTarefaDAO extends HibernateUtil{

	public ListaTarefa cadastrar(ListaTarefa listaTarefa){
		try {
			beginTransaction();
			em.persist(listaTarefa);
			commitTransaction();
			return em.merge(listaTarefa);
		} catch (Exception e) {
			rollbackTransaction();
			return null;
		}
	}

	public ListaTarefa atualizar(ListaTarefa listaTarefa){
		try {
			beginTransaction();
			listaTarefa = em.merge(listaTarefa);
			commitTransaction();
			return listaTarefa;
		} catch (Exception e) {
			rollbackTransaction();
			return null;
		} 
	}
	
	public ListaTarefa findById(long id){
		try {
			return (ListaTarefa) em.find(ListaTarefa.class,id);
		} catch (Exception e) {
			return null;
		} 
	}
	
	@SuppressWarnings("unchecked")
	public List<ListaTarefa> find(Criterion... criterion) {
		Criteria crit = ((Session) em.getDelegate())
				.createCriteria(ListaTarefa.class);
		for (Criterion c : criterion) {
			if (c != null) {
				crit.add(c);
			}
		}
		return crit.list();
	}
	
	public void remover(ListaTarefa listaTarefa){
		try{
			beginTransaction();
			for(Tarefa tarefa : listaTarefa.getTarefas()){
				new TarefaDAO().remover(tarefa);
			}
			em.remove(listaTarefa);
			commitTransaction();
		}catch(Exception e){
			rollbackTransaction();
		}
	}

}
