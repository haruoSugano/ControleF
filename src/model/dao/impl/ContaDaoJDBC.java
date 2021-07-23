package model.dao.impl;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import db.DB;
import db.DbException;
import model.dao.ContaDao;
import model.entities.Conta;

public class ContaDaoJDBC implements ContaDao{

	private Connection conn;
	
	public ContaDaoJDBC(Connection conn) {
		this.conn = conn;
	}
	
	@Override
	public void insert(Conta conta) {
		PreparedStatement ps = null;
		
		try {
			ps = conn.prepareStatement(
					"INSERT INTO tb_contaPagar"
					+"(tipoDespesa, dataVencimento, dataEmitida, despesaFixa, valor, acrescimo, formaPagamento"
					+"VALUE"
					+"(?, ?, ?, ?, ?, ?, ?)",
					Statement.RETURN_GENERATED_KEYS);
			
			ps.setString(1, conta.getTipoDespesa());
			ps.setDate(2, (Date) conta.getDataVencimento());
			ps.setDate(3, (Date) conta.getDataEmitida());
			ps.setBoolean(4, conta.getDespesaFixa());
			ps.setDouble(5, conta.getValor());
			ps.setDouble(6, conta.getAcrescimo());
			ps.setString(7, conta.getFormaPagamento());
			
			int rowAffected = ps.executeUpdate();
			
			if(rowAffected > 0) {
				ResultSet rs = ps.getGeneratedKeys();
				if(rs.next()) {
					int id = rs.getInt(1);
					conta.setId(id);
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

	@Override
	public void delete(Integer id) {
		PreparedStatement ps = null;
		try {
			ps = conn.prepareStatement("DELETE FROM tb_contaPagar WHERE id = ?");
			
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
	public List<Conta> findAll() {
		
		PreparedStatement ps = null;
		ResultSet rs = null;
		List<Conta> listConta = new ArrayList<>();
		
		try {
			ps = conn.prepareStatement("SELECT * FROM tb_contaPagar");
			
			rs = ps.executeQuery();
			
			while(rs.next()) {
				Conta conta = new Conta();
				conta.setId(rs.getInt("id"));
				conta.setTipoDespesa(rs.getString("tipoDespesa"));
				conta.setDataVencimento(rs.getDate("dataVencimento"));
				conta.setDataEmitida(rs.getDate("dataEmitida"));
				conta.setDespesaFixa(rs.getBoolean("despesaFixa"));
				conta.setValor(rs.getDouble("valor"));
				conta.setAcrescimo(rs.getDouble("acrescimo"));
				conta.setFormaPagamento(rs.getString("formaPagamento"));
				listConta.add(conta);
			}
			return listConta;
		}
		catch(SQLException e) {
			throw new DbException(e.getMessage());
		}
		finally {
			DB.closeStatement(ps);
			DB.closeStatement(ps);
		}
	}
}
