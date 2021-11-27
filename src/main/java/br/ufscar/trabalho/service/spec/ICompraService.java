package br.ufscar.trabalho.service.spec;

import br.ufscar.trabalho.domain.Cliente;
import br.ufscar.trabalho.domain.Compra;

import java.util.List;
import java.util.Optional;

public interface ICompraService {

    Compra buscarPorId(int id);

    List<Compra> buscarPorCliente(Cliente u);

    List<Compra> buscarPorCliente(int id);

    void salvar(Compra compra);

    List<Compra> buscarTodos();

    void excluir(int id);

    boolean existe(int id);
}
