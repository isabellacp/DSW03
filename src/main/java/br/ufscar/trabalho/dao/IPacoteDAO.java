package br.ufscar.trabalho.dao;

import br.ufscar.trabalho.domain.Agencia;
import br.ufscar.trabalho.domain.Pacote;
import br.ufscar.trabalho.domain.Usuario;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

// DAO do Pacote, que herda do CrudRepository, que por sua vez fornece elementos para acesso ao banco de dados
public interface IPacoteDAO extends CrudRepository<Pacote, Integer> {
    public List<Pacote> findAllByAgencia(Agencia agencia);
    public List<Pacote> findAllByAgencia_Id(int id);
    public List<Pacote> findAllByDestino(String destino);
    public Pacote findById(int id);


}