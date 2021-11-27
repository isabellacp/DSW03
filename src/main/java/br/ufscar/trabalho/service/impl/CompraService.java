package br.ufscar.trabalho.service.impl;


import br.ufscar.trabalho.dao.ICompraDAO;
import br.ufscar.trabalho.domain.Cliente;
import br.ufscar.trabalho.domain.Compra;
import br.ufscar.trabalho.service.spec.ICompraService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional(readOnly = false)
public class CompraService implements ICompraService {

    @Autowired
    ICompraDAO dao;

    @Override
    public Compra buscarPorId(int id) {
        return dao.findById(id);
    }

    @Override
    public List<Compra> buscarPorCliente(Cliente u) {
        return dao.findAllByCliente(u);
    }

    @Override
    public List<Compra> buscarPorCliente(int id) {
        return dao.findAllByCliente(id);
    }

    @Override
    public void salvar(Compra compra) {
        dao.save(compra);
    }
    @Override
    public List<Compra> buscarTodos() {
        return (List<Compra>) dao.findAll();
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
