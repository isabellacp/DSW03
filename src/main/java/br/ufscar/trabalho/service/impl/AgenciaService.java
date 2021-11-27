package br.ufscar.trabalho.service.impl;


import br.ufscar.trabalho.dao.IAgenciaDAO;
import br.ufscar.trabalho.domain.Agencia;
import br.ufscar.trabalho.domain.Usuario;
import br.ufscar.trabalho.service.spec.IAgenciaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional(readOnly = false)
public class AgenciaService implements IAgenciaService {

    @Autowired
    IAgenciaDAO dao;

    @Override
    public Agencia buscarPorId(int id) {
        return dao.findById(id);
    }

    @Override
    public Agencia buscarPorUsuario(Usuario u) {
        return dao.findAgenciaByUsuario(u);
    }

    @Override
    public Agencia buscarPorUsuario(int id) {
        return dao.findAgenciaByUsuario_Id(id);
    }

    @Override
    public void salvar(Agencia agencia) {
        dao.save(agencia);
    }

    @Override
    public List<Agencia> buscarTodos() {
        return (List<Agencia>) dao.findAll();
    }

    @Override
    public void excluir(int id) {
        dao.deleteById(id);
    }

    @Override
    public boolean agenciaTemPacotes(int id) {
        return !((long) dao.findById(id).getPacotes().size() == 0);
    }

    @Override
    public boolean existe(int id) {
        return dao.existsById(id);
    }
}
