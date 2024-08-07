package br.com.ana.dao;

import br.com.ana.domain.Marca;
import org.hibernate.Session;

public class MarcaDAO extends GenericDAOImpl<Marca> implements IMarcaDAO {
    public MarcaDAO(Session sessao) {
        super(sessao, Marca.class);
    }
}
