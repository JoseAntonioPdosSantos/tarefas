package br.com.tarefas.model.util;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import org.hibernate.Session;

public class HibernateUtil {

	protected static EntityManagerFactory emFactory;
	protected static EntityManager em;
	private static Session session;

	static {
		emFactory = Persistence.createEntityManagerFactory("persistence-unit");
		em = emFactory.createEntityManager();
		if (session == null)
			session = (Session) em.getDelegate();
	}

	protected Session getSession() {
		return session;
	}

	protected void beginTransaction() {
		em.getTransaction().begin();
	}

	protected void commitTransaction() {
		em.getTransaction().commit();
	}

	protected void rollbackTransaction() {
		em.getTransaction().rollback();
	}
	
}
