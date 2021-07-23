package model.dao;

import java.util.List;

import model.entities.Usuario;

public interface UsuarioDao {

	void insert(Usuario usuario);

	List<Usuario> findAll();

	void deleteById(Integer id);

}
