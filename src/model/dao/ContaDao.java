package model.dao;

import java.util.List;

import model.entities.Conta;

public interface ContaDao {

	void insert(Conta conta);

	List<Conta> findAll();

	void delete(Integer id);

}
