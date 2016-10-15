package br.com.tarefas.model.service;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import br.com.tarefas.model.persistence.dao.ListaTarefaDAO;
import br.com.tarefas.model.persistence.entity.ListaTarefa;

@Path("/task_lists")
public class ListaTarefaService {

	private final String CHARSET = ";charset=utf-8";
	
	private ListaTarefaDAO listaTarefaDAO;
	
	@PostConstruct
	private void init() {
		listaTarefaDAO = new ListaTarefaDAO();
	}
	
	@POST
	@Path("/add")
	@Produces(MediaType.APPLICATION_JSON + CHARSET)
	@Consumes(MediaType.APPLICATION_JSON + CHARSET)
	public ListaTarefa cadastrarListaTarefa(ListaTarefa listaTarefa){
		return null;
	}

	@PUT
	@Path("/update/{task_list_id}")
	@Consumes(MediaType.APPLICATION_JSON + CHARSET)
	@Produces(MediaType.APPLICATION_JSON + CHARSET)
	public ListaTarefa atualizarListaTarefa(ListaTarefa listaTarefa,@PathParam("task_list_id") int task_list_id){
		return null; 
	}
	
	@GET
	@Path("/list/{id}")
	@Produces(MediaType.APPLICATION_JSON + CHARSET)
	@Consumes(MediaType.TEXT_PLAIN)
	public List<ListaTarefa> listarListaTarefa(@PathParam("id") int id){
		return null;
	}
	
	@DELETE
	@Path("/remove/{task_list_id}")
	@Consumes(MediaType.TEXT_PLAIN)
	public void removerListaTarefa(@PathParam("task_list_id") int task_list_id){
		
	}

	
}
