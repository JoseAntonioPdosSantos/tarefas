package br.com.tarefas.model.persistence.dao;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Criterion;

import br.com.tarefas.model.persistence.entity.Cor;
import br.com.tarefas.model.util.HibernateUtil;

public class CorDAO  extends HibernateUtil{

	public Cor cadastrarCor(Cor cor){
		try {
			beginTransaction();
			em.merge(cor);
			commitTransaction();
			return cor;
		} catch (Exception e) {
			rollbackTransaction();
			return null;
		}
	}
	
	@SuppressWarnings("unchecked")
	public List<Cor> listarCores(Criterion... criterion) {
		Criteria crit = ((Session) em.getDelegate())
				.createCriteria(Cor.class);
		for (Criterion c : criterion) {
			if (c != null) {
				crit.add(c);
			}
		}
		return crit.list();
	}
	
}
