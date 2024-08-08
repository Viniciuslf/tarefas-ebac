package br.com.jau.dao;



import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import br.com.jau.dao.jdbc.ConnectionFactory;
import br.com.jau.domain.Produto;

public class ProdutoDAO implements IProdutoDAO {

	@Override
	public Integer cadastrar(Produto produto) throws Exception {
		Connection connection = null;
		PreparedStatement stm = null;
		try {
			connection = ConnectionFactory.getConnection();
			String sql = "INSERT INTO TB_PRODUTO_M29 (ID, CODIGO, NOME) VALUES (NEXTVAL('SQ_PRODUTO'),?,?)";
			stm = connection.prepareStatement(sql);
			stm.setString(1, produto.getCodigo());
			stm.setString(2, produto.getNome());
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
	public Integer atualizar(Produto produto) throws Exception {
		Connection connection = null;
		PreparedStatement stm = null;
		try {
			connection = ConnectionFactory.getConnection();
			String sql = "UPDATE TB_PRODUTO_M29 SET NOME = ?, CODIGO = ?";
			stm = connection.prepareStatement(sql);
			stm.setString(1, produto.getNome());
			stm.setString(2, produto.getCodigo());
			return stm.executeUpdate();
		} catch(Exception e) {
			throw e;
		} finally {
			connection.close();
		}
	}

	@Override
	public Produto consultar(String codigo) throws Exception {
		Connection connection = null;
		PreparedStatement stm = null;
		ResultSet rs = null;
		Produto produto = null;
		try {
			connection = ConnectionFactory.getConnection();
			String sql = "SELECT * FROM TB_PRODUTO_M29 WHERE CODIGO = ?";
			stm = connection.prepareStatement(sql);
			stm.setString(1, codigo);
			rs = stm.executeQuery();
			if (rs.next()) {
				produto = new Produto();
				produto.setId(rs.getLong("id"));
				produto.setCodigo(rs.getString("codigo"));
				produto.setNome(rs.getString("nome"));
			}
			return produto;
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
	public List<Produto> consultarAll() throws Exception {
		Connection connection = null;
		PreparedStatement stm = null;
		ResultSet rs = null;
		List<Produto> lista = new ArrayList<>();
		try {
			connection = ConnectionFactory.getConnection();
			String sql = "SELECT * FROM TB_PRODUTO_M29";
			stm = connection.prepareStatement(sql);
			rs = stm.executeQuery();
			
			while (rs.next()) {
				Produto produto = new Produto();
				produto.setId(rs.getLong("id"));
				produto.setCodigo(rs.getString("codigo"));
				produto.setNome(rs.getString("nome"));
				lista.add(produto);
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
	public Integer excluir(Produto produtoDB) throws Exception {
		Connection connection = null;
		PreparedStatement stm = null;
		try {
			connection = ConnectionFactory.getConnection();
			String sql = "DELETE FROM TB_PRODUTO_M29 WHERE CODIGO = ?";
			stm = connection.prepareStatement(sql);
			stm.setString(1, produtoDB.getCodigo());
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
