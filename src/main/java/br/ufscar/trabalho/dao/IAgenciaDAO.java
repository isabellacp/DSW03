package br.ufscar.trabalho.dao;

import br.ufscar.trabalho.domain.Agencia;
import br.ufscar.trabalho.domain.Usuario;
import org.springframework.data.repository.CrudRepository;
// DAO da Agencia, que herda do CrudRepository, que por sua vez fornece elementos para acesso ao banco de dados
public interface IAgenciaDAO extends CrudRepository<Agencia, Integer> {
    public Agencia findAgenciaByUsuario(Usuario u);
    public Agencia findAgenciaByUsuario_Id(int usuario_id);
    public Agencia findById(int id);

}