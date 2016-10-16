package br.com.tarefas.model.service;

import javax.annotation.PostConstruct;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
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
	@Path("/add")
	@Consumes(MediaType.APPLICATION_JSON + CHARSET)
	@Produces(MediaType.APPLICATION_JSON + CHARSET)
	public Tarefa cadastrarTarefa(Tarefa tarefa) {
		if(tarefa != null){
			if(tarefa.getListaTarefa() != null){
				if(tarefa.getListaTarefa().getId() > 0){
					if(tarefa.getDescricao() != null && !tarefa.getDescricao().trim().isEmpty()){
						if(tarefa.getStatus() != null){
							return tarefaDAO.cadastrarTarefa(tarefa);
						}
					}
				}
			}
		}
		return null;
	}

	@PUT
	@Path("/update")
	@Consumes(MediaType.TEXT_PLAIN)
	@Produces(MediaType.APPLICATION_JSON + CHARSET)
	public Tarefa atualizarListaTarefa(Tarefa tarefa) {
		if(tarefa != null && tarefa.getId() > 0){
			Tarefa tarefa_ = tarefaDAO.findById(tarefa.getId());
			tarefa_.setStatus(tarefa.getStatus());
			return tarefaDAO.atualizarListaTarefa(tarefa_);
		}
		return null;
	}
}
