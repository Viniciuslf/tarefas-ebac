package br.com.jau;


import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.util.List;

import org.junit.Test;

import br.com.jau.dao.ClienteDAO;
import br.com.jau.dao.IClienteDAO;
import br.com.jau.domain.Cliente;

public class ClienteTest {
	
	@Test
	public void cadastrarTest() throws Exception {
		IClienteDAO dao = new ClienteDAO();
		
		//TESTE CADASTRO
		Cliente cliente = new Cliente();
		cliente.setCodigo("1");
		cliente.setNome("Jailson");
		
		Integer quant = dao.cadastrar(cliente);
		assertTrue(quant == 1);
		
		//TESTE ALTERAÇÃO
		cliente.setCodigo("2");
		cliente.setNome("Outro nome");
		
		Integer quantAlt = dao.atualizar(cliente);
		assertTrue(quantAlt == 1);
		
		//TESTE CONSULTA ID
		Cliente clienteBD = dao.consultar(cliente.getCodigo());
		assertNotNull(clienteBD);
		assertNotNull(clienteBD.getId());
		assertEquals(cliente.getCodigo(), clienteBD.getCodigo());
		assertEquals(cliente.getNome(), clienteBD.getNome());
		
		//TESTE CONSULTA ALL
		List<Cliente> listaClientes = dao.consultarAll();
		assertNotNull(listaClientes);
		for (Cliente clienteBDAll : listaClientes) {
	        assertNotNull(clienteBDAll.getId());
	        assertNotNull(clienteBDAll.getCodigo());
	        assertNotNull(clienteBDAll.getNome());
		}
		
		//TESTE EXCLUIR
		Integer quantDel = dao.excluir(clienteBD);
		assertNotNull(quantDel);
	}

}
