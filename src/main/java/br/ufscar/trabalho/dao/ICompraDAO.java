package br.ufscar.trabalho.dao;

import br.ufscar.trabalho.domain.Cliente;
import br.ufscar.trabalho.domain.Compra;
import br.ufscar.trabalho.domain.Usuario;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

// DAO da Compra, que herda do CrudRepository, que por sua vez fornece elementos para acesso ao banco de dados
public interface ICompraDAO extends CrudRepository<Compra, Integer> {
    public List<Compra> findAllByCliente(Cliente cliente);
    public List<Compra> findAllByCliente(int id);
    public Compra findById(int id);

}