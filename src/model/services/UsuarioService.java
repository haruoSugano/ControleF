package model.services;

import java.util.ArrayList;
import java.util.List;

import model.entities.Usuario;

public class UsuarioService {

	public List<Usuario> findAll(){
		List<Usuario> list = new ArrayList<>();
		list.add(new Usuario(1, "Helio", 26));
		list.add(new Usuario(2, "Haruo", 20));
		list.add(new Usuario(3, "Takamori", 56));
		return list;
	}
}
