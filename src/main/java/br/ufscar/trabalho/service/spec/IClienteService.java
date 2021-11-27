package br.ufscar.trabalho.service.spec;

import br.ufscar.trabalho.domain.Cliente;
import br.ufscar.trabalho.domain.Usuario;

import java.util.List;
import java.util.Optional;

public interface IClienteService {

    Cliente buscarPorId(int id);

    Cliente buscarPorUsuario(Usuario u);

    Cliente buscarPorUsuario(int id);

    void salvar(Cliente cliente);

    List<Cliente> buscarTodos();

    void excluir(int id);

    boolean existe(int id);
}
