package br.com.tarefas.test;

import static org.junit.Assert.assertNotNull;

import org.junit.Test;

import br.com.tarefas.model.persistence.dao.ListaTarefaDAO;
import br.com.tarefas.model.persistence.dao.TarefaDAO;
import br.com.tarefas.model.persistence.entity.ListaTarefa;
import br.com.tarefas.model.persistence.entity.Status;
import br.com.tarefas.model.persistence.entity.Tarefa;
import br.com.tarefas.model.service.TarefaService;

public class TarefaTest {

	// @Test
	public void cadastrarTarefa() {

		ListaTarefa listaTarefa = new ListaTarefaDAO().findById(8L);

		Tarefa tarefa = new Tarefa();
		tarefa.setDescricao("Arroz");
		tarefa.setListaTarefa(listaTarefa);
		tarefa.setStatus(Status.A_FAZER);

		TarefaService tarefaService = new TarefaService();
		tarefa = tarefaService.cadastrarTarefa(tarefa);
		assertNotNull(tarefa);
	}

//	@Test
	public void atualizarListaTarefa() {

		Tarefa tarefa = new TarefaDAO().findById(37);
		tarefa.setStatus(Status.FEITO);

		TarefaService tarefaService = new TarefaService();
		tarefa = tarefaService.atualizarTarefa(tarefa);
		assertNotNull(tarefa);
	}
}
