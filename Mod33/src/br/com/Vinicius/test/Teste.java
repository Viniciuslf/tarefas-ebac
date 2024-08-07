package br.com.ana.test;

import br.com.ana.dao.*;
import br.com.ana.domain.*;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.util.List;

import static org.junit.Assert.*;

public class Teste {

    private EntityManagerFactory emf;
    private EntityManager em;

    private IMarcaDAO marcaDAO;
    private IAcessorioDAO acessorioDAO;
    private ICarroDAO carroDAO;

    @Before
    public void setUp() {
        // Configuração do Hibernate via JPA
        emf = Persistence.createEntityManagerFactory("ExemploJPA");
        em = emf.createEntityManager();

        // Inicializando os DAOs
        marcaDAO = new MarcaDAO(em.unwrap(org.hibernate.Session.class));
        acessorioDAO = new AcessorioDAO(em.unwrap(org.hibernate.Session.class));
        carroDAO = new CarroDAO(em.unwrap(org.hibernate.Session.class));

        // Limpar as tabelas antes de cada teste
        em.getTransaction().begin();
        em.createQuery("DELETE FROM Carro").executeUpdate();
        em.createQuery("DELETE FROM Acessorio").executeUpdate();
        em.createQuery("DELETE FROM Marca").executeUpdate();
        em.getTransaction().commit();
    }

    @After
    public void tearDown() {
        // Limpar as tabelas após cada teste
        em.getTransaction().begin();
        List<Carro> carros = carroDAO.buscarTodos();
        carros.forEach(carroDAO::excluir);
        List<Acessorio> acessorios = acessorioDAO.buscarTodos();
        acessorios.forEach(acessorioDAO::excluir);
        List<Marca> marcas = marcaDAO.buscarTodos();
        marcas.forEach(marcaDAO::excluir);
        em.getTransaction().commit();

        if (em != null) {
            if (em.getTransaction().isActive()) {
                em.getTransaction().rollback();
            }
            em.close();
        }
        if (emf != null) {
            emf.close();
        }
    }

    @Test
    public void testCadastrarEntidade() {
        try {
            em.getTransaction().begin();

            // Criação de exemplo
            Marca marca = new Marca();
            marca.setNome("Toyota");

            // Salvando a entidade
            marcaDAO.salvar(marca);
            em.getTransaction().commit();

            // Verificar se a entidade foi salva corretamente
            assertNotNull(marcaDAO.buscarPorId(marca.getId()));
        } catch (Exception e) {
            if (em.getTransaction().isActive()) {
                em.getTransaction().rollback();
            }
            throw e;
        }
    }

    @Test
    public void testAtualizarEntidade() {
        try {
            em.getTransaction().begin();

            // Criação de exemplo
            Marca marca = new Marca();
            marca.setNome("Toyota");

            // Salvando a entidade
            marcaDAO.salvar(marca);
            em.getTransaction().commit();

            // Atualizando a entidade
            em.getTransaction().begin();
            marca.setNome("Toyota Atualizada");
            marcaDAO.atualizar(marca);
            em.getTransaction().commit();

            // Verificar se a entidade foi atualizada corretamente
            Marca marcaAtualizada = marcaDAO.buscarPorId(marca.getId());
            assertEquals("Toyota Atualizada", marcaAtualizada.getNome());
        } catch (Exception e) {
            if (em.getTransaction().isActive()) {
                em.getTransaction().rollback();
            }
            throw e;
        }
    }

    @Test
    public void testBuscarTodasEntidades() {
        try {
            em.getTransaction().begin();

            // Criação de exemplos
            Marca marca1 = new Marca();
            marca1.setNome("Toyota");

            Marca marca2 = new Marca();
            marca2.setNome("Honda");

            // Salvando as entidades
            marcaDAO.salvar(marca1);
            marcaDAO.salvar(marca2);
            em.getTransaction().commit();

            // Verificar se todas as entidades foram salvas corretamente
            List<Marca> marcas = marcaDAO.buscarTodos();
            assertEquals(2, marcas.size());
        } catch (Exception e) {
            if (em.getTransaction().isActive()) {
                em.getTransaction().rollback();
            }
            throw e;
        }
    }

    @Test
    public void testExcluirEntidade() {
        try {
            em.getTransaction().begin();

            // Criação de exemplo
            Marca marca = new Marca();
            marca.setNome("Toyota");

            // Salvando a entidade
            marcaDAO.salvar(marca);
            em.getTransaction().commit();

            // Excluindo a entidade
            em.getTransaction().begin();
            marcaDAO.excluir(marca);
            em.getTransaction().commit();

            // Verificar se a entidade foi excluída corretamente
            assertNull(marcaDAO.buscarPorId(marca.getId()));
        } catch (Exception e) {
            if (em.getTransaction().isActive()) {
                em.getTransaction().rollback();
            }
            throw e;
        }
    }
}
