package br.com.tarefas.test;

import java.io.IOException;
import java.net.MalformedURLException;

import org.apache.commons.mail.EmailException;
import static org.junit.Assert.*;
import org.junit.Test;

import br.com.tarefas.model.persistence.entity.Usuario;
import br.com.tarefas.model.service.UsuarioService;

public class UsuarioTest {

	// @Test
	public void cadastrarUsuario() {
		Usuario usuario = new Usuario();
		usuario.setNome("Davi Martins dos Santos");
		usuario.setEmail("davi@gmail.com");
		usuario.setSenha("123");

		UsuarioService usuarioService = new UsuarioService();
		String retorno = usuarioService.cadastrarUsuario(usuario);

		assertEquals("Usuário cadastrado.", retorno);

	}

	// @Test
	public void atualizarCadastro() {
		Usuario usuario = new Usuario();
		usuario.setId(2L);
		usuario.setNome("Davi Martins dos Santos");
		usuario.setEmail("davi_martins_dos_santos@gmail.com");
		usuario.setSenha("123");

		UsuarioService usuarioService = new UsuarioService();
		String retorno = usuarioService.atualizarCadastro(usuario);

		assertEquals("Usuário Atualizado.", retorno);
	}

	@Test
	public void autenticarPeloFacebook() throws MalformedURLException, IOException {
		String token = "EAASiLCxWITIBADlRc3EN59lDZAQZBiK2gZA4n0m6aZC0oXilxVSM2gE1Oe4fUKHi6xLlVAzjUiIQ7t0VHh4C1vjL6ceY9Le0ZBpNOb4E28MBujIxBVxFjwV5SPSy83a1bpxUhMoAgmy0oadSorFMAZCiXEXEbLWHM1LzeEwRxjPgZDZD";
		UsuarioService usuarioService = new UsuarioService();
		String retorno = usuarioService.autenticar(null, null, token);
		assertEquals("redirect:/", retorno);
	}

	public void resetarSenha() throws EmailException {

	}

}
