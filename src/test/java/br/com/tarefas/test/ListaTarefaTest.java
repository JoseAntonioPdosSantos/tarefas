package br.com.tarefas.test;

import static org.junit.Assert.assertNotNull;

import java.util.List;

import org.junit.Test;

import br.com.tarefas.model.persistence.dao.ListaTarefaDAO;
import br.com.tarefas.model.persistence.dao.UsuarioDAO;
import br.com.tarefas.model.persistence.entity.Cor;
import br.com.tarefas.model.persistence.entity.ListaTarefa;
import br.com.tarefas.model.persistence.entity.Usuario;
import br.com.tarefas.model.service.ListaTarefaService;

public class ListaTarefaTest {

//	@Test
	public void cadastrarListaTarefa() {
		Cor cor = new Cor();
		cor.setNomeCor("Verde");
		
		Usuario usuario = new Usuario();
		usuario = new UsuarioDAO().findById(1L);
		
		ListaTarefa listaTarefa = new ListaTarefa();
		listaTarefa.setCor(cor);
		listaTarefa.setNome("Compras");
		listaTarefa.setUsuario(usuario);
		
		ListaTarefaService listaTarefaService = new ListaTarefaService();
		listaTarefa = listaTarefaService.cadastrarListaTarefa(listaTarefa);
		assertNotNull(listaTarefa);
		
	}

//	@Test
	public void atualizarListaTarefa() {
		Cor cor = new Cor();
		cor.setNomeCor("Vermelho");
		
		ListaTarefa listaTarefa = new ListaTarefaDAO().findById(6);
		listaTarefa.setCor(cor);
		listaTarefa.setNome("Vendas");
		
		ListaTarefaService listaTarefaService = new ListaTarefaService();
		listaTarefa = listaTarefaService.atualizarListaTarefa(listaTarefa);
		assertNotNull(listaTarefa);
	}

//	@Test
	public void listarListaTarefa() {
		ListaTarefaService listaTarefaService = new ListaTarefaService();
		List<ListaTarefa> listaTarefa = listaTarefaService.listarListaTarefa(2L);
		assertNotNull(listaTarefa);
	}

//	@Test
	public void removerListaTarefa() {
		ListaTarefaService listaTarefaService = new ListaTarefaService();
		listaTarefaService.removerListaTarefa(6L);
	}

}
