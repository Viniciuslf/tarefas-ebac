package br.com.ana.dao;

import br.com.ana.domain.Acessorio;
import org.hibernate.Session;

public class AcessorioDAO extends GenericDAOImpl<Acessorio> implements IAcessorioDAO {
    public AcessorioDAO(Session sessao) {
        super(sessao, Acessorio.class);
    }
}
