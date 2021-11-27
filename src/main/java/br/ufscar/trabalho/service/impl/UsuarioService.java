package br.ufscar.trabalho.service.impl;


import br.ufscar.trabalho.dao.IUsuarioDAO;
import br.ufscar.trabalho.domain.Agencia;
import br.ufscar.trabalho.domain.Usuario;
import br.ufscar.trabalho.service.spec.IUsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional(readOnly = false)
public class UsuarioService implements IUsuarioService {

    @Autowired
    IUsuarioDAO dao;

    @Override
    public Usuario buscarPorId(int id) {
        return dao.findById(id);
    }

    @Override
    public void salvar(Usuario usuario) {
        dao.save(usuario);
    }

    @Override
    public List<Usuario> buscarTodos() {
        return (List<Usuario>) dao.findAll();
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
