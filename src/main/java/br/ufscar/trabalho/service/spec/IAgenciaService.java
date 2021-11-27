package br.ufscar.trabalho.service.spec;

import br.ufscar.trabalho.domain.Agencia;
import br.ufscar.trabalho.domain.Usuario;

import java.util.List;
import java.util.Optional;

public interface IAgenciaService {

    Agencia buscarPorId(int id);

    Agencia buscarPorUsuario(Usuario u);

    Agencia buscarPorUsuario(int id);

    void salvar(Agencia agencia);

    List<Agencia> buscarTodos();

    void excluir(int id);

    boolean agenciaTemPacotes(int id);

    boolean existe(int id);
}
