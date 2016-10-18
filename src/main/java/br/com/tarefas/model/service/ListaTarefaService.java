package br.com.tarefas.model.service;

import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Restrictions;

import br.com.tarefas.model.persistence.dao.CorDAO;
import br.com.tarefas.model.persistence.dao.ListaTarefaDAO;
import br.com.tarefas.model.persistence.dao.UsuarioDAO;
import br.com.tarefas.model.persistence.entity.Cor;
import br.com.tarefas.model.persistence.entity.ListaTarefa;
import br.com.tarefas.model.persistence.entity.Usuario;

@Path("/task_lists")
public class ListaTarefaService {

	private final String CHARSET = ";charset=utf-8";

	private ListaTarefaDAO listaTarefaDAO;

	public ListaTarefaService() {
		listaTarefaDAO = new ListaTarefaDAO();
	}

	@POST
	@Path("/add")
	@Produces(MediaType.APPLICATION_JSON + CHARSET)
	@Consumes(MediaType.APPLICATION_JSON + CHARSET)
	public ListaTarefa cadastrarListaTarefa(ListaTarefa listaTarefa) {

		if (listaTarefa != null) {
			if (listaTarefa.getUsuario() != null) {
				if (listaTarefa.getUsuario().getEmail() != null) {
					Criterion email = Restrictions.eq("email", listaTarefa.getUsuario().getEmail());
					List<Usuario> usuarios = new UsuarioDAO().find(email);
					if (usuarios != null && usuarios.size() > 0) {
						listaTarefa.setUsuario(usuarios.get(0));
					} else {
						return null;
					}
				} else {
					return null;
				}
			} else {
				return null;
			}
			if (listaTarefa.getCor() != null) {
				listaTarefa.setCor(cadastrarCor(listaTarefa.getCor()));
			}
			if(listaTarefa.getNome() == null || listaTarefa.getNome().trim().isEmpty())return null;
			Criterion nome = Restrictions.eq("nome", listaTarefa.getNome());
			Criterion usuario = Restrictions.eq("usuario.id", listaTarefa.getUsuario().getId());
			List<ListaTarefa> listaTarefas = listaTarefaDAO.find(nome,usuario);
			if(listaTarefas != null && listaTarefas.size() > 0) return null;
			return listaTarefaDAO.cadastrar(listaTarefa);
		}
		return null;

	}

	public Cor cadastrarCor(Cor cor) {
		if (cor.getNomeCor() != null && !cor.getNomeCor().trim().isEmpty()) {
			CorDAO corDAO = new CorDAO();
			Criterion nome = Restrictions.eq("nomeCor", cor.getNomeCor());
			List<Cor> cores = corDAO.listar(nome);
			if (cores != null && cores.size() > 0) {
				return corDAO.atualizar(cores.get(0));
			}
			return corDAO.cadastrar(cor);
		}
		return cor;
	}

	@PUT
	@Path("/update")
	@Consumes(MediaType.APPLICATION_JSON + CHARSET)
	@Produces(MediaType.APPLICATION_JSON + CHARSET)
	public ListaTarefa atualizarListaTarefa(ListaTarefa listaTarefa) {
		if (listaTarefa == null || listaTarefa.getId() < 1)
			return null;
		ListaTarefa listaTarefa_ = listaTarefaDAO.findById(listaTarefa.getId());
		if (listaTarefa.getCor() == null)
			return null;
		listaTarefa_.setCor(cadastrarCor(listaTarefa.getCor()));
		listaTarefa_.setNome(listaTarefa.getNome());
		return listaTarefaDAO.atualizar(listaTarefa_);
	}

	@GET
	@Path("/list/{id}")
	@Produces(MediaType.APPLICATION_JSON + CHARSET)
	@Consumes(MediaType.TEXT_PLAIN)
	public List<ListaTarefa> listarListaTarefa(@PathParam("id") long id) {
		Criterion usuario = Restrictions.eq("usuario.id", id);
		return listaTarefaDAO.find(usuario);
	}

	@DELETE
	@Path("/remove/{task_list_id}")
	@Consumes(MediaType.TEXT_PLAIN)
	@Produces(MediaType.TEXT_PLAIN)
	public String removerListaTarefa(@PathParam("task_list_id") long task_list_id) {
		ListaTarefa listaTarefa = listaTarefaDAO.findById(task_list_id);
		try{
			listaTarefaDAO.remover(listaTarefa);
			return "Lista de Tarefa removida com sucesso.";
		}catch(Exception e){
			return null;
		}
	}

}
