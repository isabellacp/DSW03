package br.ufscar.trabalho.service.impl;


import br.ufscar.trabalho.dao.IClienteDAO;
import br.ufscar.trabalho.domain.Cliente;
import br.ufscar.trabalho.domain.Usuario;
import br.ufscar.trabalho.service.spec.IClienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional(readOnly = false)
public class ClienteService implements IClienteService {

    @Autowired
    IClienteDAO dao;

    @Override
    public Cliente buscarPorId(int id) {
        return dao.findById(id);
    }

    @Override
    public Cliente buscarPorUsuario(Usuario u) {
        return dao.findByUsuario(u);
    }

    @Override
    public Cliente buscarPorUsuario(int id) {
        return dao.findClienteByUsuarioId(id);
    }

    @Override
    public void salvar(Cliente cliente) {
        dao.save(cliente);
    }
    @Override
    public List<Cliente> buscarTodos() {
        return (List<Cliente>) dao.findAll();
    }

    @Override
    public void excluir(int id) {
        dao.deleteById(id);
    }


    @Override
    public boolean existe(int id) {
        return dao.existsById(id);
    }
}
