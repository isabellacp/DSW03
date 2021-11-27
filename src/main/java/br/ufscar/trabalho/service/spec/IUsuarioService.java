package br.ufscar.trabalho.service.spec;

import br.ufscar.trabalho.domain.Usuario;

import java.util.List;
import java.util.Optional;

public interface IUsuarioService {

    Usuario buscarPorId(int id);

    void salvar(Usuario compra);

    List<Usuario> buscarTodos();

    void excluir(int id);

    boolean existe(int id);
}
