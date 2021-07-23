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
import model.dao.UsuarioDao;
import model.entities.Usuario;

public class UsuarioDaoJDBC implements UsuarioDao {
	
	/**
	 * Dependência:
	 */
	private Connection conn;
	
	public UsuarioDaoJDBC(Connection conn) {
		this.conn = conn;
	}
	
	/**
	 * Inserir ao banco de dados, dados do usuário:
	 */
	@Override
	public void insert(Usuario usuario) {
		PreparedStatement ps = null;
		
		try {
			ps = conn.prepareStatement(
					"INSERT INTO tb_usuario "
					+"(nome, idade ) "
					+"VALUES"
					+"(?, ? )",
					Statement.RETURN_GENERATED_KEYS);
			
			ps.setString(1, usuario.getNome());
			ps.setInt(2, usuario.getIdade());
			
			int rowAffected = ps.executeUpdate();
			
			if(rowAffected > 0) {
				ResultSet rs = ps.getGeneratedKeys();
				if(rs.next()) {
					int id = rs.getInt(1);
					usuario.setId(id);
				}
				DB.closeResultSet(rs);
			}
			else {
				throw new DbException("Erro!");
			}
		}
		catch(SQLException e) {
			throw new DbException(e.getMessage());
		}
		finally {
			DB.closeConnection();
		}
	}
	
	/**
	 * Deletar o usuario atráves do ID:
	 */
	@Override
	public void deleteById(Integer id) {
		PreparedStatement ps = null;
		try {
			ps = conn.prepareStatement("DELETE FROM tb_usuario WHERE id = ?");
			
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
	
	/**
	 * Buscar todos os usuário do banco de dados:
	 */
	@Override
	public List<Usuario> findAll() {
		
		PreparedStatement ps = null;
		ResultSet rs = null;
		List<Usuario> listUsuario = new ArrayList<>();
		
		try {
			ps = conn.prepareStatement("SELECT * FROM tb_usuario");
			
			rs = ps.executeQuery();
			
			while(rs.next()) {
				Usuario usuario = new Usuario();
				usuario.setId(rs.getInt("id"));
				usuario.setNome(rs.getString("nome"));
				usuario.setIdade(rs.getInt("idade"));
				listUsuario.add(usuario);
			}
		}
		catch(SQLException e) {
			throw new DbException(e.getMessage());
		}
		finally {
			DB.closeStatement(ps);
			DB.closeResultSet(rs);
		}
		return listUsuario;
	}
}
