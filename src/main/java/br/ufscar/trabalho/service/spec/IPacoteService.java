package br.ufscar.trabalho.service.spec;

import br.ufscar.trabalho.domain.Pacote;
import br.ufscar.trabalho.domain.Agencia;

import java.util.List;
import java.util.Optional;

public interface IPacoteService {

    Pacote buscarPorId(int id);

    List<Pacote> buscarPorDestino(String destino);

    List<Pacote> buscarPorAgencia(Agencia u);

    List<Pacote> buscarPorAgencia(int id);

    void salvar(Pacote pacote);

    List<Pacote> buscarTodos();

    void excluir(int id);

    boolean existe(int id);
}
