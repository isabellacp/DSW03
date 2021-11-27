package br.ufscar.trabalho.service.impl;


import br.ufscar.trabalho.dao.IPacoteDAO;
import br.ufscar.trabalho.domain.Agencia;
import br.ufscar.trabalho.domain.Pacote;
import br.ufscar.trabalho.service.spec.IPacoteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional(readOnly = false)
public class PacoteService implements IPacoteService {

    @Autowired
    IPacoteDAO dao;

    @Override
    public Pacote buscarPorId(int id) {
        return dao.findById(id);
    }

    @Override
    public List<Pacote> buscarPorDestino(String destino) {
        return dao.findAllByDestino(destino);
    }

    @Override
    public List<Pacote> buscarPorAgencia(Agencia a) {
        return dao.findAllByAgencia(a);
    }

    @Override
    public List<Pacote> buscarPorAgencia(int id) {
        return dao.findAllByAgencia_Id(id);
    }

    @Override
    public void salvar(Pacote agencia) {
        dao.save(agencia);
    }

    @Override
    public List<Pacote> buscarTodos() {
        return (List<Pacote>) dao.findAll();
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
