package br.com.tarefas.test;

import static org.junit.Assert.*;

import java.io.IOException;
import java.net.MalformedURLException;

import org.apache.commons.mail.EmailException;
import org.junit.Test;

import br.com.tarefas.model.persistence.dao.UsuarioDAO;
import br.com.tarefas.model.persistence.entity.Usuario;
import br.com.tarefas.model.service.UsuarioService;

public class UsuarioTest {

	 @Test
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

//	@Test
	public void autenticarPeloFacebook() throws MalformedURLException, IOException {
		UsuarioService usuarioService = new UsuarioService();
		Usuario usuario = new UsuarioDAO().findById(2L);
		String retorno = usuarioService.autenticar(null, null, usuario.getTokenFacebook());
		assertEquals("redirect:/", retorno);
	}
	
//	@Test
	public void resetarSenha() throws EmailException {
		UsuarioService usuarioService = new UsuarioService();
		String retorno = usuarioService.resetarSenha("davi@gmail.com");
		assertEquals("Senha alterada e enviada via e-mail.",retorno);
	}
	
	
}
