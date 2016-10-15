package br.com.tarefas.model.service;

import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Restrictions;

import br.com.tarefas.model.persistence.dao.UsuarioDAO;
import br.com.tarefas.model.persistence.entity.Usuario;

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
	@Consumes(MediaType.APPLICATION_JSON + CHARSET )
	public String cadastrarUsuario(Usuario usuario) {
		if(isEmailCadastrado(usuario)){
			return "Email já existe na base de dados.";
		}
		if(usuarioDAO.cadastrarUsuario(usuario)){
			return "Usuário cadastrado.";
		}
		return null;
	}

	private boolean isEmailCadastrado(Usuario usuario){
		Criterion email = Restrictions.eq("email", usuario.getEmail());
		List<Usuario> usuarios = usuarioDAO.find(email);
		return (usuarios != null && usuarios.size() > 0);
	}
	
	private boolean isEmailPodeSerAtualizado(Usuario usuario){
		Criterion email = Restrictions.eq("email", usuario.getEmail());
		Criterion usuario_ = Restrictions.ne("usuario", usuario);
		List<Usuario> usuarios = usuarioDAO.find(email,usuario_);
		return !(usuarios != null && usuarios.size() > 0);
	}
	
	@PUT
	@Path("/update")
	@Produces(MediaType.TEXT_PLAIN)
	@Consumes(MediaType.APPLICATION_JSON + CHARSET )
	public String atualizarCadastro(Usuario usuario) {
		if(isEmailPodeSerAtualizado(usuario)){
			if(usuarioDAO.atualizarCadastro(usuario)){
				return "Usuário Atualizado.";
			}
		}else{
			return "Email já existe na base de dados.";
		}
		return null;
	}

	@POST
	@Path("/authenticate")
	@Produces(MediaType.TEXT_PLAIN)
	@Consumes(MediaType.APPLICATION_JSON + CHARSET )
	public String autenticarPeloFacebook(String token) {

		return null;
	}

//	@POST
//	@Path("/authenticate")
//	@Produces(MediaType.APPLICATION_JSON + CHARSET )
//	@Consumes(MediaType.APPLICATION_JSON + CHARSET )
//	public Usuario autenticarPeloEmail(String email, String senha) {
//		return null;
//	}

	@POST
	@Path("/forgot_password")
	@Consumes(MediaType.TEXT_PLAIN)
	@Produces(MediaType.TEXT_PLAIN)
	public String resetarSenha(String email) {
		return null;
	}

}