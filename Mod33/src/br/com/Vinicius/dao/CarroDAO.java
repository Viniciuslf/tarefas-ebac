package br.com.ana.dao;

import br.com.ana.domain.Carro;
import org.hibernate.Session;

public class CarroDAO extends GenericDAOImpl<Carro> implements ICarroDAO {
    public CarroDAO(Session sessao) {
        super(sessao, Carro.class);
    }
}
