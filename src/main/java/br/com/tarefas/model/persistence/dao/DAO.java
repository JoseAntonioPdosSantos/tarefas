package br.com.tarefas.model.persistence.dao;

import java.util.List;

import org.hibernate.criterion.Criterion;

public interface DAO<T> {
	
	public T cadastrar(T t);
	
	public T atualizar(T t);
	
	public T findById(long id);
	
	public List<T> listar(Criterion... criterion);
}
