package br.ufscar.trabalho.dao;

import br.ufscar.trabalho.domain.Usuario;
import org.springframework.data.repository.CrudRepository;

// DAO do usuário, que herda do CrudRepository, que por sua vez fornece elementos para acesso ao banco de dados
public interface IUsuarioDAO extends CrudRepository<Usuario, Integer> {
    public Usuario getUsuarioByEmail(String email);
    public Usuario findById(int id);
}