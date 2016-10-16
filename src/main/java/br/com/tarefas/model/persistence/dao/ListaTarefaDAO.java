package br.com.tarefas.model.persistence.dao;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Criterion;

import br.com.tarefas.model.persistence.entity.ListaTarefa;
import br.com.tarefas.model.persistence.entity.Usuario;
import br.com.tarefas.model.util.HibernateUtil;

public class ListaTarefaDAO extends HibernateUtil{

	public ListaTarefa cadastrarListaTarefa(ListaTarefa listaTarefa){
		try {
			beginTransaction();
			em.persist(listaTarefa);
			commitTransaction();
			return listaTarefa;
		} catch (Exception e) {
			rollbackTransaction();
			return null;
		}
	}

	public ListaTarefa atualizarListaTarefa(ListaTarefa listaTarefa){
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
	public List<ListaTarefa> listarListaTarefa(Criterion... criterion) {
		Criteria crit = ((Session) em.getDelegate())
				.createCriteria(Usuario.class);
		for (Criterion c : criterion) {
			if (c != null) {
				crit.add(c);
			}
		}
		return crit.list();
	}
	
	public void removerListaTarefa(ListaTarefa listaTarefa){
		try{
			beginTransaction();
			em.remove(listaTarefa);
			commitTransaction();
		}catch(Exception e){
			rollbackTransaction();
		}
	}

}
