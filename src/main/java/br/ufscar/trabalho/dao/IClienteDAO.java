package br.ufscar.trabalho.dao;

import br.ufscar.trabalho.domain.Cliente;
import br.ufscar.trabalho.domain.Usuario;
import org.springframework.data.repository.CrudRepository;
// DAO do Cliente, que herda do CrudRepository, que por sua vez fornece elementos para acesso ao banco de dados
public interface IClienteDAO extends CrudRepository<Cliente, Integer> {
    public Cliente findById(int id);
    public Cliente findByUsuario(Usuario u);
    public Cliente findClienteByUsuarioId(int id);

}