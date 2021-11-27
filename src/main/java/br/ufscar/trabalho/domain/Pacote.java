package br.ufscar.trabalho.domain;

import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
// Diretiva para definir a tabela dessa classe no banco de dados.
@Table(name = "pacote")
// Diretiva para definir essa classes como uma entidade
@Entity
public class Pacote {
    // Diretiva que indica que o campo id será a chave primaria
    @Id
    // Diretiva que indica que o valor da coluna de ID sera gerado pelo auto increment do banco de dados
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Integer id;

    // Diretiva que indica varios pacotes podem ser da mesma agencia
    @ManyToOne(optional = false)
    // Diretiva que facilita
    @JoinColumn(name = "agencia_id", nullable = false)
    private Agencia agencia;

    @Column(name = "destino", length = 30)
    private String destino;

    @Column(name = "partida")
    private String partida;

    @Column(name = "duracao")
    private Integer duracao;

    @Column(name = "valor")
    private Integer valor;

    public Integer getValor() {
        return valor;
    }

    public void setValor(Integer valor) {
        this.valor = valor;
    }

    public Integer getDuracao() {
        return duracao;
    }

    public void setDuracao(Integer duracao) {
        this.duracao = duracao;
    }

    public String getPartida() {
        return partida;
    }

    public void setPartida(String partida) {
        this.partida = partida;
    }

    public String getDestino() {
        return destino;
    }

    public void setDestino(String destino) {
        this.destino = destino;
    }

    public Agencia getAgencia() {
        return agencia;
    }

    public void setAgencia(Agencia agencia) {
        this.agencia = agencia;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
}