package com.jau;

import com.jau.dao.*;
import com.jau.domain.Acessorio;
import com.jau.domain.Carro;
import com.jau.domain.Marca;

import static org.junit.Assert.*;
import org.junit.Test;

public class CarroTest {

    private ICarroDao carroDao;
    private IMarcaDao marcaDao;
    private IAcessorioDao acessorioDao;

    public CarroTest() {
        carroDao = new CarroDao();
        marcaDao = new MarcaDao();
        acessorioDao = new AcessorioDao();
    }

    @Test
    public void cadastrar() {
        Carro carro = criarCarro("CARRO");
        assertNotNull(carro);
        assertNotNull(carro.getId());

    }

    private Carro criarCarro(String placa) {
        Marca marca = criarMarca("M1");
        Acessorio acessorio1 = criarAcessorio("A1");

        Carro carro = new Carro();
        carro.setPlaca(placa);
        carro.setModelo("Corolla");
        carro.setMarca(marca);
        carro.add(acessorio1);
        Carro carroCadastrado = carroDao.cadastrar(carro);
        return carroCadastrado;
    }

    private Acessorio criarAcessorio(String codigo) {
        Acessorio acessorio = new Acessorio();
        acessorio.setCodigo(codigo);
        acessorio.setNome("GPS");
        return acessorio;
    }

    private Marca criarMarca(String codigo) {
        Marca marca = new Marca();
        marca.setCodigo(codigo);
        marca.setNome("Toyota");
        Marca marcaCadastrada = marcaDao.cadastrar(marca);
        return marcaCadastrada;
    }
}
