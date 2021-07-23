package model.dao;

import java.util.List;

import model.entities.Entrada;

public interface EntradaDao {

	void insert(Entrada entrada);

	void deleteId(Integer id);

	List<Entrada> findAll();

}
