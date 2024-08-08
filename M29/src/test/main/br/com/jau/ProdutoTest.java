package br.com.jau;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.util.List;

import org.junit.Test;

import br.com.jau.dao.IProdutoDAO;
import br.com.jau.dao.ProdutoDAO;
import br.com.jau.domain.Produto;

public class ProdutoTest {
	
	@Test
	public void cadastrarTest() throws Exception {
		IProdutoDAO dao = new ProdutoDAO();
		
		Produto produto = new Produto();
		produto.setCodigo("1");
		produto.setNome("Notebook");
		
		Integer quant = dao.cadastrar(produto);
		assertTrue(quant == 1);
		
		
		produto.setCodigo("2");
		produto.setNome("Outro nome prod");
		
		Integer quantAlt = dao.atualizar(produto);
		assertTrue(quantAlt == 1);
		
		
		Produto produtoDB = dao.consultar(produto.getCodigo());
		assertNotNull(produtoDB);
		assertNotNull(produtoDB.getId());
		assertNotNull(produtoDB.getCodigo(), produtoDB.getCodigo());
		assertNotNull(produtoDB.getNome(), produtoDB.getNome());
		
		
		List<Produto> listaProdutos = dao.consultarAll();
		assertNotNull(listaProdutos);
		for (Produto produtoBDAll : listaProdutos) {
			assertNotNull(produtoBDAll.getId());
			assertNotNull(produtoBDAll.getCodigo());
			assertNotNull(produtoBDAll.getNome());
		}
		
		
		Integer quantDel = dao.excluir(produtoDB);
		assertNotNull(quantDel);
		
	}

}
