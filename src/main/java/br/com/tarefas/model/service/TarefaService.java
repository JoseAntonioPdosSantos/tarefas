package br.com.tarefas.model.service;

import javax.annotation.PostConstruct;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import br.com.tarefas.model.persistence.dao.TarefaDAO;
import br.com.tarefas.model.persistence.entity.Tarefa;

@Path("/task_lists")
public class TarefaService {

	private TarefaDAO tarefaDAO;
	
	private final String CHARSET = ";charset=utf-8";
	
	
	@PostConstruct
	private void init() {
		tarefaDAO = new TarefaDAO();
	}
	
	@POST
	@Path("/add/{task_list_id}")
	@Consumes(MediaType.APPLICATION_JSON + CHARSET)
	@Produces(MediaType.APPLICATION_JSON + CHARSET)
	public Tarefa cadastrarTarefa(Tarefa tarefa, @PathParam("task_list_id") int task_list_id) {
		return null;
	}

	@PUT
	@Path("/update/{task_list_id}/tasks/{task_id}")
	@Consumes(MediaType.TEXT_PLAIN)
	@Produces(MediaType.APPLICATION_JSON + CHARSET)
	public Tarefa atualizarListaTarefa(@PathParam("task_list_id") int task_list_id, @PathParam("task_id") int task_id) {
		return null;
	}
}
