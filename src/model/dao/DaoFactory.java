package model.dao;

import model.dao.impl.ContaDaoJDBC;
import model.dao.impl.EntradaDaoJDBC;
import model.dao.impl.UsuarioDaoJDBC;
import db.DB;

public class DaoFactory {

	public static ContaDao createContaDao() {
		return new ContaDaoJDBC(DB.getConnection());
	}
	
	public static EntradaDao createEntradaDao() {
		return new EntradaDaoJDBC(DB.getConnection());
	}
	
	public static UsuarioDao createUsuarioDao() {
		return new UsuarioDaoJDBC(DB.getConnection());
	}
}
