package br.com.tarefas.model.service;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import br.com.tarefas.model.persistence.dao.TarefaDAO;
import br.com.tarefas.model.persistence.entity.Tarefa;

@Path("/tasks")
public class TarefaService {

	private TarefaDAO tarefaDAO;
	
	private final String CHARSET = ";charset=utf-8";
	
	public TarefaService() {
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
							return tarefaDAO.cadastrar(tarefa);
						}
					}
				}
			}
		}
		return null;
	}

	@PUT
	@Path("/update")
	@Consumes(MediaType.APPLICATION_JSON + CHARSET)
	@Produces(MediaType.APPLICATION_JSON + CHARSET)
	public Tarefa atualizarTarefa(Tarefa tarefa) {
		if(tarefa != null && tarefa.getId() > 0){
			Tarefa tarefa_ = tarefaDAO.findById(tarefa.getId());
			tarefa_.setStatus(tarefa.getStatus());
			return tarefaDAO.atualizar(tarefa_);
		}
		return null;
	}
}
