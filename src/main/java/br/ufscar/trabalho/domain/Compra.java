package br.ufscar.trabalho.domain;

import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;

// Diretiva para definir a tabela dessa classe no banco de dados.
@Table(name = "compra")
// Diretiva para definir essa classes como uma entidade
@Entity
public class Compra {
    @Id
    // Diretiva que indica que o valor da coluna de ID sera gerado pelo auto increment do banco de dados
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Integer id;

    // ManyToOne pois varias compras podem referenciar o mesmo pacote
    @ManyToOne
    // Diretiva que facilita o acesso a pacotes (mas no banco de dados ainda temos apenas o pacote_id
    @JoinColumn(name = "pacote_id")
    private Pacote pacote;

    // ManyToOne pois varias compras podem referenciar o mesmo cliente
    @ManyToOne
    // Diretiva que facilita o acesso a clientes (mas no banco de dados ainda temos apenas o cliente_id
    @JoinColumn(name = "cliente_id")
    private Cliente cliente;

    @Column(name = "data")
    private String data;

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public Pacote getPacote() {
        return pacote;
    }

    public void setPacote(Pacote pacote) {
        this.pacote = pacote;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
}