package br.com.tarefas.model.persistence.dao;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Criterion;

import br.com.tarefas.model.persistence.entity.Usuario;
import br.com.tarefas.model.util.HibernateUtil;

public class UsuarioDAO extends HibernateUtil {

	public boolean cadastrarUsuario(Usuario usuario) {
		try {
			beginTransaction();
			em.persist(usuario);
			commitTransaction();
			return true;
		} catch (Exception e) {
			rollbackTransaction();
			return false;
		}
	}

	public boolean atualizarCadastro(Usuario usuario) {
		try {
			beginTransaction();
			em.merge(usuario);
			commitTransaction();
			return true;
		} catch (Exception e) {
			rollbackTransaction();
			return false;
		}
	}

	public Usuario findById(long id){
		try {
			return (Usuario) em.find(Usuario.class,id);
		} catch (Exception e) {
			return null;
		} 
	}
	
	@SuppressWarnings("unchecked")
	public List<Usuario> find(Criterion... criterion) {
		Criteria crit = ((Session) em.getDelegate())
				.createCriteria(Usuario.class);
		for (Criterion c : criterion) {
			if (c != null) {
				crit.add(c);
			}
		}
		return crit.list();
	}


}
