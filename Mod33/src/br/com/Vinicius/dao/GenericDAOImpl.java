package br.com.ana.dao;

import org.hibernate.Session;
import java.util.List;

public class GenericDAOImpl<T> implements GenericDAO<T> {
    private final Session sessao;
    private final Class<T> classeEntidade;

    public GenericDAOImpl(Session sessao, Class<T> classeEntidade) {
        this.sessao = sessao;
        this.classeEntidade = classeEntidade;
    }

    @Override
    public void salvar(T entidade) {
        sessao.save(entidade);
    }

    @Override
    public void atualizar(T entidade) {
        sessao.update(entidade);
    }

    @Override
    public void excluir(T entidade) {
        sessao.delete(entidade);
    }

    @Override
    public T buscarPorId(Long id) {
        return sessao.get(classeEntidade, id);
    }

    @Override
    public List<T> buscarTodos() {
        return sessao.createQuery("from " + classeEntidade.getName(), classeEntidade).list();
    }
}
