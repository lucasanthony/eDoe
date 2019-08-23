package com.edoe.Service;

import java.util.List;

import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.edoe.Model.Usuario;
import com.edoe.Repository.UsuarioDAO;

@Service
public class UsuarioService {
	@Autowired
	private UsuarioDAO usuarioDAO;
	
	public void insereUsuario(Usuario usuario) {
		usuarioDAO.save(usuario);
	}
	
	public Usuario atualizaUsuario(Usuario usuario) {
		Usuario usuarioToUpdate = usuarioDAO.findUsuarioById(usuario.getId());
		usuarioDAO.save(usuarioToUpdate);
		return usuarioToUpdate;
	}
	
	public List<Usuario> pesquisaTodosUsuarios(){
		return usuarioDAO.findAll();
	}

	public void deletarTodosUsuarios() {
		usuarioDAO.deleteAll();
	}
	public Usuario pesquisaUsuarioId(ObjectId id) {
		return usuarioDAO.findUsuarioById(id);
	}

	public String pesquisaUsuarioNome(String nome) {
		List<Usuario> usuarios = this.usuarioDAO.findAll();
		String retorno = "";
		for (Usuario usuario : usuarios) {
			if(usuario.getNome().equals(nome)) {
				retorno += usuario.toString() + " | ";
			}
		}
		return retorno;
	}
	
	public Usuario deletaUsuario(ObjectId id) {
		Usuario usuario = this.usuarioDAO.findUsuarioById(id);
		usuarioDAO.delete(usuario);
		return usuario;
	}

}
