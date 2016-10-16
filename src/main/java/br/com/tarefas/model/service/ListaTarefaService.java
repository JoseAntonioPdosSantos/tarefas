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
	
	@PostConstruct
	private void init() {
		listaTarefaDAO = new ListaTarefaDAO();
	}
	
	@POST
	@Path("/add")
	@Produces(MediaType.APPLICATION_JSON + CHARSET)
	@Consumes(MediaType.APPLICATION_JSON + CHARSET)
	public ListaTarefa cadastrarListaTarefa(ListaTarefa listaTarefa){
		
		if(listaTarefa != null){
			if(listaTarefa.getUsuario() != null){
				if(listaTarefa.getUsuario().getEmail() != null){
					Criterion email = Restrictions.eq("email", listaTarefa.getUsuario().getEmail());
					List<Usuario> usuarios = new UsuarioDAO().find(email);
					if(usuarios!= null && usuarios.size() > 0){
						listaTarefa.setUsuario(usuarios.get(0));
					}else{
						return null;
					}
				}else{
					return null;
				}
			}else{
				return null;
			}
			if(listaTarefa.getCor() != null){
				if(listaTarefa.getCor().getNomeCor()!= null && !listaTarefa.getCor().getNomeCor().trim().isEmpty()){
					Cor cor = new Cor();
					cor.setNomeCor(listaTarefa.getCor().getNomeCor());
					
					CorDAO corDAO = new CorDAO();
					cor = corDAO.cadastrarCor(cor);
					if(cor != null)	{
						listaTarefa.setCor(cor);
					}else{
						return null;
					}
				}
			}
			return listaTarefaDAO.cadastrarListaTarefa(listaTarefa);
			
		}else{
			return null;
		}
	}

	@PUT
	@Path("/update/{task_list_id}")
	@Consumes(MediaType.APPLICATION_JSON + CHARSET)
	@Produces(MediaType.APPLICATION_JSON + CHARSET)
	public ListaTarefa atualizarListaTarefa(ListaTarefa listaTarefa,@PathParam("task_list_id") int task_list_id){
		ListaTarefa listaTarefa_ = listaTarefaDAO.findById(task_list_id);
		listaTarefa_.setCor(listaTarefa.getCor());
		listaTarefa_.setNome(listaTarefa.getNome());
		return listaTarefaDAO.atualizarListaTarefa(listaTarefa_); 
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
