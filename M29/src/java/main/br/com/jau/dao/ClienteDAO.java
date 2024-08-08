package br.com.jau.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import br.com.jau.dao.jdbc.ConnectionFactory;
import br.com.jau.domain.Cliente;

public class ClienteDAO implements IClienteDAO {

	@Override
	public Integer cadastrar(Cliente cliente) throws Exception {
		Connection connection = null;
		PreparedStatement stm = null;
		try {
			connection = ConnectionFactory.getConnection();
			String sql = "INSERT INTO TB_CLIENTE_M29 (ID, CODIGO, NOME) VALUES (NEXTVAL('SQ_CLIENTE'),?,?)";
			stm = connection.prepareStatement(sql);
			stm.setString(1, cliente.getCodigo());
			stm.setString(2, cliente.getNome());
			return stm.executeUpdate();
		} catch(Exception e) {
			throw e;
		} finally {
			if (stm != null && stm.isClosed()) {
				stm.close();
			}
			if (connection != null && connection.isClosed()) {
				connection.close();
			}
		}
	}
	
	@Override
	public Integer atualizar(Cliente cliente) throws Exception {
	    Connection connection = null;
	    PreparedStatement stm = null;
	    try {
	        connection = ConnectionFactory.getConnection();
	        String sql = "UPDATE TB_CLIENTE_M29 SET NOME = ?, CODIGO = ?";
	        stm = connection.prepareStatement(sql);
	        stm.setString(1, cliente.getNome());
	        stm.setString(2, cliente.getCodigo());
	        return stm.executeUpdate();
	    } catch(Exception e) {
	        throw e;
	    } finally {
	        connection.close();
	    }
	}

	@Override
	public Cliente consultar(String codigo) throws Exception {
		Connection connection = null;
		PreparedStatement stm = null;
		ResultSet rs = null;
		Cliente cliente = null;
		try {
			connection = ConnectionFactory.getConnection();
			String sql = "SELECT * FROM TB_CLIENTE_M29 WHERE CODIGO = ?";
			stm = connection.prepareStatement(sql);
			stm.setString(1, codigo);
			rs = stm.executeQuery();
			if (rs.next()) {
				cliente = new Cliente();
				cliente.setId(rs.getLong("id"));
				cliente.setCodigo(rs.getString("codigo"));
				cliente.setNome(rs.getString("nome"));
			}
			return cliente;
		} catch(Exception e) {
			throw e;
		} finally {
			if (stm != null && stm.isClosed()) {
				stm.close();
			}
			if (connection != null && connection.isClosed()) {
				connection.close();
			}
		}
	}
	
	@Override
	public List<Cliente> consultarAll() throws Exception {
	    Connection connection = null;
	    PreparedStatement stm = null;
	    ResultSet rs = null;
	    List<Cliente> lista = new ArrayList<>();
	    try {
	        connection = ConnectionFactory.getConnection();
	        String sql = "SELECT * FROM TB_CLIENTE_M29";
	        stm = connection.prepareStatement(sql);
	        rs = stm.executeQuery();
	        
	        while (rs.next()) {
	            Cliente cliente = new Cliente();
	            cliente.setId(rs.getLong("id"));
	            cliente.setCodigo(rs.getString("codigo"));
	            cliente.setNome(rs.getString("nome"));
	            lista.add(cliente);
	        }
	        
	        return lista;
	    } catch(Exception e) {
	        throw e;
	    } finally {
	        if (rs != null) {
	            rs.close();
	        }
	        if (stm != null) {
	            stm.close();
	        }
	        if (connection != null) {
	            connection.close();
	        }
	    }
	}

	

	@Override
	public Integer excluir(Cliente cliente) throws Exception {
		Connection connection = null;
		PreparedStatement stm = null;
		try {
			connection = ConnectionFactory.getConnection();
			String sql = "DELETE FROM TB_CLIENTE_M29 WHERE CODIGO = ?";
			stm = connection.prepareStatement(sql);
			stm.setString(1, cliente.getCodigo());
			return stm.executeUpdate();
		} catch(Exception e) {
			throw e;
		} finally {
			if (stm != null && stm.isClosed()) {
				stm.close();
			}
			if (connection != null && connection.isClosed()) {
				connection.close();
			}
		}
	}
	
	

}