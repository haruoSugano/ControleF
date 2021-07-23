package model.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import db.DB;
import db.DbException;
import model.dao.EntradaDao;
import model.entities.Entrada;

public class EntradaDaoJDBC implements EntradaDao{
	
	private Connection conn;
	
	public EntradaDaoJDBC(Connection conn) {
		this.conn = conn;
	}

	@Override
	public void insert(Entrada entrada) {
		
		PreparedStatement ps = null;
		
		try {
			ps = conn.prepareStatement(
					"INSERT INTO tb_entrada "
					+ "(tipoEntrada, valor, acrescimo, fixa )"
					+ "VALUE "
					+ "(?, ?, ? ? )",
					Statement.RETURN_GENERATED_KEYS);
			
			ps.setString(1, entrada.getTipoEntrada());
			ps.setDouble(2, entrada.getValor());
			ps.setDouble(3, entrada.getAcrescimo());
			ps.setBoolean(4, entrada.getFixa());
			
			int rowAffected = ps.executeUpdate();
			
			if (rowAffected > 0) {
				ResultSet rs = ps.getGeneratedKeys();
				if(rs.next()) {
					int id = rs.getInt(1);
					entrada.setId(id);
				}
				DB.closeResultSet(rs);
			}
		}
		catch(SQLException e) {
			throw new DbException(e.getMessage());
		}
		finally {
			DB.closeStatement(ps);
		}
	}

	@Override 
	public void deleteId(Integer id) {
		PreparedStatement ps = null;
		
		try {
			ps = conn.prepareStatement("DELETE FROM tb_entrada WHERE id = ?");
			
			ps.setInt(1, id);
			ps.executeUpdate();
			
		}
		catch(SQLException e) {
			throw new DbException(e.getMessage());
		}
		finally {
			DB.closeStatement(ps);
			DB.closeConnection();
		}
	}
	
	@Override
	public List<Entrada> findAll(){
		PreparedStatement ps = null;
		ResultSet rs = null;
		List<Entrada> listEntrada = new ArrayList<>();
		
		try {
			ps = conn.prepareStatement("SELECT * FROM tb_entrada");
			
			rs = ps.executeQuery();
			
			while(rs.next()) {
				Entrada entrada = new Entrada();
				entrada.setId(rs.getInt("id"));
				entrada.setTipoEntrada(rs.getString("tipoEntrada"));
				entrada.setValor(rs.getDouble("valor"));
				entrada.setAcrescimo(rs.getDouble("acrescimo"));
				entrada.setFixa(rs.getBoolean("fixa"));
				listEntrada.add(entrada);
			}
		}
		catch(SQLException e) {
			throw new DbException(e.getMessage());
		}
		finally {
			DB.closeResultSet(rs);
			DB.closeStatement(ps);
		}
		return listEntrada;
	}
}
