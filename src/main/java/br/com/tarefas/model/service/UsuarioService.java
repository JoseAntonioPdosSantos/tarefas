package br.com.tarefas.model.service;

import java.io.IOException;
import java.net.MalformedURLException;
import java.util.List;
import java.util.Random;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.apache.commons.mail.EmailException;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Restrictions;

import br.com.tarefas.model.persistence.dao.UsuarioDAO;
import br.com.tarefas.model.persistence.entity.LoginFacebook;
import br.com.tarefas.model.persistence.entity.Usuario;
import br.com.tarefas.model.persistence.entity.UsuarioFacebook;
import br.com.tarefas.model.util.ASCIIUtil;
import br.com.tarefas.model.util.EmailUtil;

@Path("/users")
public class UsuarioService {

	private UsuarioDAO usuarioDAO;
	private final String CHARSET = ";charset=utf-8";

	public UsuarioService() {
		usuarioDAO = new UsuarioDAO();
	}

	@POST
	@Path("/add")
	@Produces(MediaType.TEXT_PLAIN)
	@Consumes(MediaType.APPLICATION_JSON + CHARSET)
	public String cadastrarUsuario(Usuario usuario) {
		if (isEmailCadastrado(usuario)) {
			return "Email já existe na base de dados.";
		}
		if (usuarioDAO.cadastrar(usuario) != null) {
			return "Usuário cadastrado.";
		}
		return null;
	}

	private boolean isEmailCadastrado(Usuario usuario) {
		Criterion email = Restrictions.eq("email", usuario.getEmail());
		List<Usuario> usuarios = usuarioDAO.find(email);
		return (usuarios != null && usuarios.size() > 0);
	}

	@PUT
	@Path("/update")
	@Produces(MediaType.TEXT_PLAIN)
	@Consumes(MediaType.APPLICATION_JSON + CHARSET)
	public String atualizarCadastro(Usuario usuario) {
		if (isEmailPodeSerAtualizado(usuario)) {
			Usuario usuario_ = usuarioDAO.findById(usuario.getId());
			usuario_.setNome(usuario.getNome());
			usuario_.setEmail(usuario.getEmail());
			usuario_.setSenha(usuario.getSenha());
			if (usuarioDAO.atualizar(usuario_) != null) {
				return "Usuário Atualizado.";
			}
		} else {
			return "Email já existe na base de dados.";
		}
		return null;
	}

	private boolean isEmailPodeSerAtualizado(Usuario usuario) {
		Criterion email = Restrictions.eq("email", usuario.getEmail());
		Criterion usuario_ = Restrictions.ne("id", usuario.getId());
		List<Usuario> usuarios = usuarioDAO.find(email, usuario_);
		return !(usuarios != null && usuarios.size() > 0);
	}

	// @POST
	// @Path("/authenticate")
	// @Produces(MediaType.TEXT_PLAIN)
	// public String autenticarPeloFacebook(){
	// LoginFacebook loginFacebook = new LoginFacebook();
	// return "redirect:" + loginFacebook.getLoginRedirectURL();
	// }

	@POST
	@Path("/authenticate")
	@Produces(MediaType.TEXT_PLAIN)
	@Consumes(MediaType.APPLICATION_JSON)
	public String autenticar(Usuario usuario) throws MalformedURLException, IOException {
		if (usuario.getTokenFacebook() != null && !usuario.getTokenFacebook().trim().isEmpty()) {
			LoginFacebook loginFacebook = new LoginFacebook();

			UsuarioFacebook usuarioFacebook = loginFacebook.obterUsuarioFacebook(usuario.getTokenFacebook());
			Usuario usuario_ = new Usuario();
			usuario_.setNome(usuarioFacebook.getName());
			usuario_.setEmail(usuarioFacebook.getEmail());
			usuario_.setTokenFacebook(usuario.getTokenFacebook());

			if (isEmailCadastrado(usuario_)) {
				return "redirect:/";
			} else {
				if (usuarioDAO.cadastrar(usuario_) != null) {
					return "redirect:/";
				}
			}
			return "";
		} else {
			Criterion email_ = Restrictions.ne("email", usuario.getEmail());
			Criterion senha_ = Restrictions.ne("senha", usuario.getSenha());
			List<Usuario> usuarios = usuarioDAO.find(email_, senha_);
			if (usuarios != null && usuarios.size() == 1) {
				return "Autenticação realizada com sucesso";
			} else {
				return "Email não existe na base.";
			}
		}
	}

	@POST
	@Path("/forgot_password")
	@Consumes(MediaType.TEXT_PLAIN)
	@Produces(MediaType.TEXT_PLAIN)
	public String resetarSenha(String email) throws EmailException {

		Usuario usuario = new Usuario();
		usuario.setEmail(email);

		if (isEmailCadastrado(usuario)) {

			Criterion email_ = Restrictions.eq("email", usuario.getEmail());
			List<Usuario> usuarios = usuarioDAO.find(email_);
			if (usuarios != null && usuarios.size() > 0) {
				usuario = usuarios.get(0);
			} else {
				return "Não foi  possível alterar a senha.";
			}
			usuario.setSenha(gerarSenha());

			if (usuarioDAO.atualizar(usuario) != null) {
				EmailUtil.enviarEmail(usuario.getEmail(), usuario.getNome(), "i.t.i.core@outlook.com", "I-T-I Core","Nova Senha", "Olá "+ usuario.getNome()+ ", Obrigado por utilizar nossos serviços. Estamos lhe enviando sua nova senha. A partir de agora, toda vez que for acessar ao sistema utilize sua nova senha: " + usuario.getSenha());
				return "Senha alterada e enviada via e-mail.";
			} else {
				return "Não foi  possível alterar a senha.";
			}

		}
		return null;
	}

	private String gerarSenha() {
		char[] value = new char[6];
		for (int i = 0; i < value.length; i++) {
			value[i] = ASCIIUtil.getChar(new Random().nextInt(ASCIIUtil.length()));
		}
		return String.valueOf(value);
	}

}