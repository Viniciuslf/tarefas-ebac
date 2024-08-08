package br.com.jau.dao;

import java.util.List;

import br.com.jau.domain.Cliente;

public interface IClienteDAO {
	
	public Integer cadastrar(Cliente cliente) throws Exception;

	public Cliente consultar(String codigo) throws Exception;

	public Integer excluir(Cliente clienteBD) throws Exception;

	public Integer atualizar(Cliente cliente) throws Exception;

	public List<Cliente> consultarAll() throws Exception;

}
