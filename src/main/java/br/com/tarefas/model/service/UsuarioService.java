package br.com.tarefas.model.service;

import java.io.IOException;
import java.net.MalformedURLException;
import java.util.List;

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
		if (usuarioDAO.cadastrarUsuario(usuario)) {
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
			if (usuarioDAO.atualizarCadastro(usuario)) {
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
	@Consumes(MediaType.APPLICATION_JSON + CHARSET)
	public String autenticar(String email, String senha, String token) throws MalformedURLException, IOException {
		if (token != null && !token.trim().isEmpty()) {
			LoginFacebook loginFacebook = new LoginFacebook();

			UsuarioFacebook usuarioFacebook = loginFacebook.obterUsuarioFacebook(token);
			Usuario usuario = new Usuario();
			usuario.setNome(usuarioFacebook.getFirstName() + " " + usuarioFacebook.getLastName());
			usuario.setEmail(usuarioFacebook.getEmail());
			usuario.setTokenFacebook(token);

			if (isEmailCadastrado(usuario)) {
				return "redirect:/";
			} else {
				if (usuarioDAO.cadastrarUsuario(usuario)) {
					return "redirect:/";
				}
			}
			return "";
		} else {
			Criterion email_ = Restrictions.ne("id", email);
			Criterion senha_ = Restrictions.ne("id", senha);
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
		
		if(isEmailCadastrado(usuario)){
			
			Criterion email_ = Restrictions.eq("email", usuario.getEmail());
			List<Usuario> usuarios = usuarioDAO.find(email_);
			if(usuarios != null && usuarios.size() > 0){
				usuario = usuarios.get(0);
			}else{
				return "Não foi  possível alterar a senha";
			}
			usuario.setSenha(gerarSenha());
			
			if(usuarioDAO.atualizarCadastro(usuario)){
				EmailUtil.enviarEmail("joseantonio.dev@gail.com","José Antonio","i.t.i.core@outlook.com","I-T-I Core","Altere sua senha","Altere sua senha clicando aqui: ");
			}else{
				return "Não foi  possível alterar a senha";
			}
			
		}
		return null;
	}
	
	private String gerarSenha(){
		char[] value = new char[6];
		for(int i = 0; i < value.length; i++){
			value[i] = (char) (Math.random() * 255);
		}
		return String.valueOf(value);
	}

}