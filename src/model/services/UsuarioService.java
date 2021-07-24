package model.services;

import java.util.ArrayList;
import java.util.List;

import model.dao.DaoFactory;
import model.dao.UsuarioDao;
import model.entities.Usuario;

public class UsuarioService {
	
	private UsuarioDao dao = DaoFactory.createUsuarioDao();

	/**
	 * Busca os dados do usuário no banco de dados
	 * @return Uma lista de dados do usuário
	 */
	public List<Usuario> findAll(){
		return dao.findAll();
	}
	
	/**
	 * É um método para salvar ou atualizar o cadastro de usuario
	 * Caso o usuário já esteja cadastrado, ele será atualizado.
	 * @param usuario
	 */
	public void saveOrUpdate(Usuario usuario) {
		if (usuario.getId() == null) {
			dao.insert(usuario);
		}
		else {
			
		}
	}
}
